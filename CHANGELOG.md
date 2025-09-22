
## [3.1.0] 
### <3.1.0> Readme & Documentation Improvements

- **Expanded User Guide**: Added detailed explanations and step-by-step instructions for all existing features.

- **Improved Examples**: Included practical examples for wallet management, Plutus script deployment, and Haskell diagnostics.

- **Clearer Error Guidance**: Updated instructions to help users resolve common issues with Ghcid and Cardano CLI setup.

- **Enhanced Feature Descriptions**: Added more context and usage tips for diagnostic tools, wallet management, and API integration.

- **Formatting & Readability**: Improved markdown structure, headings, and code blocks in the readme for easier navigation.


### <3.0.9> Diagnostics feature and Debug Tools 

- **Error Highlighting**: Instantly highlights errors in Haskell files.

- **Error Suggestions**: Provides actionable suggestions for quick fixes.

- **Automatic Document Monitoring**: Monitors open Haskell files and updates diagnostics as you edit.

- **Notification System**: Alerts users when Ghcid is not installed.

- **Cabal Debug Runner**: Seamlessly run and debug Haskell projects using Cabal, with REPL integration.

- **Integrated Debug Console**: Launches GHCi with clear status messages.

- **Context-Aware Configuration**: Automatically detects Cabal projects and sets up debug configurations.

- **UTXO Fetcher**: Retrieves latest UTXO details for any Cardano script address.


### <3.0.6> Wallet Management and Deployment

- **Blockfrost API Validator**: Validates API keys for mainnet, preprod, and preview networks.

- **Wallet Generator**: Securely generates a new Cardano wallet with mnemonic seed phrase.

- **Wallet Restore**: Restore any Cardano wallet using a 24-word mnemonic seed phrase.

- **Send ADA Dialog**: Transfer ADA to any Cardano address with metadata support.

- **View Balance & Transactions**: Fetch real-time wallet balance and last 5 transactions.

- **Receive ADA Support**: Display and copy base address for receiving payments.

- **Plutus Script Address Builder**: Generate Cardano addresses for Plutus scripts.

- **Network Support**: preview, preprod, mainnet with automatic flag handling.

- **Cardano CLI Validation**: Ensures cardano-node and cardano-cli are installed.

- **Automatic File Handling**: Saves generated script addresses to `.addr` files.

- **Notifications**: Success and error messages are displayed in IntelliJ.


### <3.0.5> Cardano API Integration, Syntax Highlighting and Code Completion

- **CardanoScan API Client**: Fetches blockchain data including blocks, network state, transactions, pools, assets, and governance info.

- **Extensive API Coverage**: Latest block, network state, protocol parameters, pool info/status/list, reward account info, governance keys, DRep info, governance actions, asset details, policy details, transaction lists.

- **Dynamic Queries**: Converts user inputs into valid API requests.

- **Terminal Integration**: Displays API responses in dedicated terminal.

- **Context-Aware Coloring**: Differentiates functions, types, and operators for improved readability.

- **Multi-Line & Doc Support**: Properly highlights multi-line comments, doc comments, and string literals.

- **Comprehensive Element Coverage**: Highlights keywords, numbers, strings, comments, doc comments, pragmas, operators, constructors, function names, variables, types, and function declarations.

- **Context-Sensitive Suggestions**: Offers completions based on the current scope and code context.

- **Quick Insertions**: Automatically inserts boilerplate or required syntax for selected suggestions.

- **Wide Language Element Support**: Provides intelligent suggestions for varid, conid, q_name, q_var_con, q_con, ttype, atype, expressions, module declarations, import declarations, data/newtype declarations, class/instance/type declarations, literals, pragmas, comments, Haddock, qq, and directives.

- **Fuzzy Matching:**: Finds relevant completions even with partial input.

- **Performance Optimized**: Real-time suggestions without noticeable IDE lag.