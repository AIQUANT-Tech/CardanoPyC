
## [3.1.0] - 2025-09-20

### Readme & Documentation Improvements

- **Expanded User Guide**: Added detailed explanations and step-by-step instructions for all existing features.

- **Improved Examples**: Included practical examples for wallet management, Plutus script deployment, and Haskell diagnostics.

- **Clearer Error Guidance**: Updated instructions to help users resolve common issues with Ghcid and Cardano CLI setup.

- **Enhanced Feature Descriptions**: Added more context and usage tips for diagnostic tools, wallet management, and API integration.

- **Formatting & Readability**: Improved markdown structure, headings, and code blocks in the readme for easier navigation.

## [3.0.9] - 2025-08-15

### Diagnostic Feature

- **Error Highlighting**: Instantly highlights errors in Haskell files.

- **Error Suggestions**: Provides actionable suggestions for quick fixes.

- **Gutter Icons**: Displays intuitive error and warning icons in the editor gutter with tooltips.

- **Automatic Document Monitoring**: Monitors open Haskell files and updates diagnostics as you edit.

- **Debounced Restart**: Efficiently restarts Ghcid after edits with a 500ms debounce.

- **Cabal Project Support**: Detects Cabal projects and runs `cabal repl` for diagnostics.

- **Notification System**: Alerts users when Ghcid is not installed.

### Debug Tools

- **Cabal Debug Runner**: Seamlessly run and debug Haskell projects using Cabal, with REPL integration.

- **Integrated Debug Console**: Launches GHCi with clear status messages.

- **Context-Aware Configuration**: Automatically detects Cabal projects and sets up debug configurations.

- **Async Execution**: Runs all processes asynchronously for smooth IDE performance.

- **UTXO Fetcher**: Retrieves latest UTXO details for any Cardano script address.

- **Datum Handling**: Automatically fetches datum hash or inline datum for each UTXO.

- **Interactive Popup**: Displays UTXO and datum details in a scrollable dialog.

## [3.0.6] - 2025-07-30

### Wallet Management

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

### Deployment of Smart Contract

- **Plutus Script Address Builder**: Generate Cardano addresses for Plutus scripts.

- **Network Support**: preview, preprod, mainnet with automatic flag handling.

- **Cardano CLI Validation**: Ensures cardano-node and cardano-cli are installed.

- **Interactive Dialog**: Input script file path and select network type.

- **Automatic File Handling**: Saves generated script addresses to `.addr` files.

- **Notifications**: Success and error messages are displayed in IntelliJ.

- **Background Execution**: Runs address generation in a separate thread.

- **Error Handling**: Detects missing files, invalid paths, and CLI errors.

## [3.0.5] - 2025-06-20

### Cardano API Integration

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

### Syntax Highlighting

- **Context-Aware Coloring**: Differentiates functions, types, and operators for improved readability.

- **Multi-Line & Doc Support**: Properly highlights multi-line comments, doc comments, and string literals.

- **Comprehensive Element Coverage**: Highlights keywords, numbers, strings, comments, doc comments, pragmas, operators, constructors, function names, variables, types, and function declarations.

- **Pragma Recognition**: Detects compiler pragmas and displays them distinctively.

- **Error Detection Support**: Highlights invalid or incomplete syntax to assist in debugging.

### Code Completion

- **Context-Sensitive Suggestions**: Offers completions based on the current scope and code context.

- **Quick Insertions**: Automatically inserts boilerplate or required syntax for selected suggestions.

- **Wide Language Element Support**: Provides intelligent suggestions for varid, conid, q_name, q_var_con, q_con, ttype, atype, expressions, module declarations, import declarations, data/newtype declarations, class/instance/type declarations, literals, pragmas, comments, Haddock, qq, and directives.

- **Fuzzy Matching:**: Finds relevant completions even with partial input.

- **Performance Optimized**: Real-time suggestions without noticeable IDE lag.