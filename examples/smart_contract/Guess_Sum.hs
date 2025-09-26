{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE TypeApplications #-}
{-# LANGUAGE DataKinds #-}
{-# LANGUAGE NoImplicitPrelude #-}
{-# LANGUAGE TemplateHaskell #-}
{-# LANGUAGE ScopedTypeVariables #-}
{-# LANGUAGE DeriveAnyClass #-}
{-# LANGUAGE DeriveGeneric #-}

module Guess_Sum (writeHelloWorldScript) where

import qualified Prelude
import Prelude (FilePath, IO, Show (..), ($), (>>=))

import Cardano.Api.Shelley (PlutusScript (..))
import Cardano.Api(
    writeFileTextEnvelope,
    FileError,
    Script(PlutusScript),
    PlutusScriptVersion(PlutusScriptV2)
  )
import Codec.Serialise (serialise)
import qualified Data.ByteString.Lazy as LBS
import qualified Data.ByteString.Short as SBS
import System.Directory (createDirectoryIfMissing)
import System.FilePath (takeDirectory)

import Plutus.V2.Ledger.Api (Validator, mkValidatorScript, ScriptContext, unValidatorScript, BuiltinData, unsafeFromBuiltinData)
import PlutusTx (compile, unstableMakeIsData)
import PlutusTx.Prelude hiding (($), (<>))
import PlutusTx.Builtins (BuiltinData)

data SumDatum = SumDatum
    { num1 :: Integer
    , num2 :: Integer
    }
PlutusTx.unstableMakeIsData ''SumDatum

newtype SumRedeemer = SumRedeemer Integer
PlutusTx.unstableMakeIsData ''SumRedeemer

{-# INLINABLE mkSumValidator #-}
mkSumValidator :: SumDatum -> SumRedeemer -> ScriptContext -> Bool
mkSumValidator (SumDatum a b) (SumRedeemer guess) _ =
    if guess == (a + b)
        then True
        else traceError "Wrong sum!"

{-# INLINABLE wrap #-}
wrap :: (SumDatum -> SumRedeemer -> ScriptContext -> Bool)
     -> BuiltinData -> BuiltinData -> BuiltinData -> ()
wrap f d r c =
    if f (unsafeFromBuiltinData d)
         (unsafeFromBuiltinData r)
         (unsafeFromBuiltinData c)
    then ()
    else traceError "Validation failed"

validator :: Validator
validator = mkValidatorScript $$(compile [|| wrap mkSumValidator ||])

writeValidator :: FilePath -> Validator -> IO (Prelude.Either (FileError ()) ())
writeValidator file val = do
    createDirectoryIfMissing True (takeDirectory file)
    let serialized = serialise (unValidatorScript val)
    let strictBytes = LBS.toStrict serialized
    let sbs = SBS.toShort strictBytes
    let script = PlutusScript PlutusScriptV2 (PlutusScriptSerialised sbs)
    result <- writeFileTextEnvelope file Nothing script
    case result of
        Prelude.Left err -> do
            Prelude.putStrLn ("Error: " Prelude.<> Prelude.show err)
            return (Prelude.Left err)
        Prelude.Right () -> do
            Prelude.putStrLn ("Successfully wrote script to: " Prelude.<> file)
            return (Prelude.Right ())


writeHelloWorldScript :: IO (Prelude.Either (FileError ()) ())
writeHelloWorldScript = writeValidator "output/hello-world.plutus" validator
