# Examples

This document showcases the **Diagnostic Feature**, **Debug Tools**, **Wallet Management**, **Smart Contract Deployment**, **Cardano API Integration**, **Syntax Highlighting**, and **Code Completion** along with video demonstrations.


## 📺 Example Videos

🎥 **Diagnostic Feature & Debug Tools Demo**  
[Watch on YouTube](https://youtu.be/LfSuzCy3qWs?si=Rlcn_nZhMb23DErP)

---

🎥 **Wallet Management & Smart Contract Deployment Demo**  
[Watch on YouTube](https://youtu.be/Dq8NNB8RPOg?si=YXsrQc-g_D__ecy6)

---

🎥 **Cardano API Integration, Code Completion & Syntax Highlighting Demo**  
[Watch on YouTube](https://www.youtube.com/watch?v=zGLwGJFA9Y4)

## 🚑 Diagnostic Feature

- **Error Highlighting**: Instantly highlights errors in Haskell files.
- **Error Suggestions**: Provides actionable suggestions for quick fixes.
- **Automatic Document Monitoring**: Continuously monitors open Haskell files and updates diagnostics as you edit.
- **Debounced Restart**: Efficiently restarts `ghcid` after edits with a 500ms debounce.
- **Cabal Project Support**: Detects Cabal projects and runs `cabal repl` for diagnostics.
- **Notification System**: Alerts users when `ghcid` is not installed.

---

#### Diagnostic Error 📸
![Diagnostics Error](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/diagnostics_error.png?raw=true)


#### Diagnostic error suggestion 📸
![Diagnostic Error Suggestion](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/diagnostic_error_suggestion.png?raw=true)

🎞️ Diagnostics Demo (GIF):  
![Diagnostic Feature Demo](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/diagnostic_feature.gif?raw=true)

## 🐞 Debug Tools

- **Cabal Debug Runner**: Seamlessly run and debug Haskell projects using Cabal, with REPL integration.
- **Integrated Debug Console**: Launches GHCi with clear status messages.
- **Context-Aware Configuration**: Automatically detects Cabal projects and sets up debug configurations.
- **Async Execution**: Runs all processes asynchronously for smooth IDE performance.
- **UTXO Fetcher**: Retrieves the latest UTXO details for any Cardano script address.
- **Datum Handling**: Automatically fetches datum hash or inline datum for each UTXO.
- **Interactive Popup**: Displays UTXO and datum details in a scrollable dialog.

#### Debug Button 📸
![Debug Button](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/debug_button.png?raw=true)


#### Debug Error 📸
![Debug Error](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/debug_error.png?raw=true)


🎞️ Debug Demo (GIF):  
![Debug Tools Demo](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/Debug_tools.gif?raw=true)

---

## 👛 Wallet Management

- **Blockfrost API Validator**: Validates API keys for mainnet, preprod, and preview networks.
- **Wallet Generator**: Securely generates a new Cardano wallet with mnemonic seed phrase.
- **Wallet Restore**: Restore any Cardano wallet using a 24-word mnemonic seed phrase.
- **Secure Storage Integration**: Mnemonic, addresses, and credentials are encrypted.
- **Interactive Wallet Dialog**: View, copy, and store wallet details with clear recovery instructions.
- **Send ADA Dialog**: Transfer ADA to any Cardano address with metadata support.
- **View Balance & Transactions**: Fetch real-time wallet balance and last 5 transactions.
- **Wallet Actions Panel**: Centralized UI to Send, Receive, View Balance, and Logout.
- **Receive ADA Support**: Display and copy base address for receiving payments.
- **Secure Logout**: Clears all sensitive wallet data.

---

#### Wallet Management 📸
![Wallet Management](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/wallet_management.png?raw=true)

🎞️ Wallet Management Demo (GIF):  
![Wallet Management Demo](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/wallet_management.gif?raw=true)

## 📜 Deployment of Smart Contract

- **Plutus Script Address Builder**: Generate Cardano addresses for Plutus scripts.
- **Network Support**: preview, preprod, mainnet with automatic flag handling.
- **Cardano CLI Validation**: Ensures cardano-node and cardano-cli are installed.
- **Interactive Dialog**: Input script file path and select network type.
- **Automatic File Handling**: Saves generated script addresses to `.addr` files.
- **Notifications**: Success and error messages are displayed in IntelliJ.
- **Background Execution**: Runs address generation in a separate thread.
- **Error Handling**: Detects missing files, invalid paths, and CLI errors.

---

#### Deployment Of Smart Contract 📸
![Deployment](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/Deployment.png?raw=true)


🎞️ Deployment Demo (GIF):  
![Deployment of Smart Contract Demo](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/Deployment.gif?raw=true)


---


## 🔗 Cardano API Integration

- **CardanoScan API Client**: Fetches blockchain data including blocks, network state, transactions, pools, assets, and governance info.
- **Extensive API Coverage**: Latest block, network state, protocol parameters, pool info/status/list, reward account info, governance keys, DRep info, governance actions, asset details, policy details, transaction lists.
- **Dynamic Queries**: Converts user inputs into valid API requests.
- **Thread-Safe Session Management**: Stores API keys safely across threads.
- **API Key Handling**: Prompts user if missing and caches it securely.
- **Async Data Fetching**: Runs API calls in background threads.
- **JSON Formatting**: Pretty-prints raw JSON responses.
- **Terminal Integration**: Displays API responses in dedicated terminal.
- **Exception Handling**: Shows descriptive error dialogs for failed requests.
- **Reusable Functional Interface**: Generic `ApiClientFunction` reduces boilerplate.

#### Blockchain api 📸
![Blockchain api](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/Blockchain_api.png?raw=true)


#### Blockchain data 📸
![Blockchain data](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/Blockchain_data.png?raw=true)

---

## 🎨 Syntax Highlighting 

- **Context-Aware Coloring**: Differentiates functions, types, and operators for improved readability.
- **Multi-Line & Doc Support**: Properly highlights multi-line comments, doc comments, and string literals.
- **Comprehensive Element Coverage**: Highlights keywords, numbers, strings, comments, doc comments, pragmas, operators, constructors, function names, variables, types, and function declarations.
- **Pragma Recognition**: Detects compiler pragmas and displays them distinctively.
- **Error Detection Support**: Highlights invalid or incomplete syntax to assist in debugging.

#### Syntax Highlighting 📸
![Syntax Highlighting](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/syntax_highlighting.png?raw=true)


---

## ⚡ Code Completion 

- **Context-Sensitive Suggestions**: Offers completions based on the current scope and code context.
- **Quick Insertions**: Automatically inserts boilerplate or required syntax for selected suggestions.
- **Wide Language Element Support**: Provides intelligent suggestions for varid, conid, q_name, q_var_con, q_con, ttype, atype, expressions, module declarations, import declarations, data/newtype declarations, class/instance/type declarations, literals, pragmas, comments, Haddock, qq, and directives.
- **Fuzzy Matching**: Finds relevant completions even with partial input.
- **Performance Optimized**: Real-time suggestions without noticeable IDE lag.


#### Code Completion 📸
![Code Completion](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/code_completion.png?raw=true)


🎞️ Code Completion Demo (GIF):  
![code Completion Demo](https://github.com/AIQUANT-Tech/CardanoPyC/blob/main/examples/code_completion.gif?raw=true)
