# LangCursor

![Build](https://github.com/lauvsong/LangCursor/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/23464.svg)](https://plugins.jetbrains.com/plugin/23464)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/23464.svg)](https://plugins.jetbrains.com/plugin/23464)

> LangCursor is an IntelliJ-based JetBrains IDE plugin designed to help developers working with non-English languages avoid typos by automatically changing the cursor color.

![](https://github.com/lauvsong/LangCursor/blob/main/medias/langcursor.gif)

<!-- Plugin description -->

## What's LangCursor? 🤔

LangCursor is a plugin designed specifically **for developers who work in non-English languages 🌍**.  
It addresses the common pain point of typos caused by language switching.

### Cursor Color Change for Non-English Languages 🔵🔴🟢
- **Automatic Cursor Color Change**
  - The primary function of LangCursor is to help you avoid typos. It does this by automatically changing the color of your cursor when you're typing in a non-English language.
- **Vim-friendly**
  - LangCursor also integrates seamlessly with the 'Insert Mode' cursor, a feature commonly used in conjunction with Vim emulation plugins.

### Supported Environments 🌐
- Supported OS: Windows, macOS
- Not using any third-party Input Method editor(IME), opposed to the default OS input methods

## How to Use
Once installed, LangCursor will be automatically executed when you open your project in IDE.

## Customization
You can customize the color of the cursor for non-English input methods.

1. Go to `Settings` > `Tools` > `LangCursor`.
2. Choose your preferred color for the non-English input method.
3. Apply the changes.

## Troubleshooting
> **Note**  
> Please ensure you've reviewed the [Supported Environments](#supported-environments-) section for any compatibility issues specific to your system.

LangCursor may encounter many local-specific edge cases.  
If you encounter any bugs or challenges while using LangCursor, we're here to help. Here's what you can do:

- First, check if the issue has already been reported or resolved in our [GitHub issues](https://github.com/lauvsong/LangCursor/issues).
- If you don't find a similar issue, feel free to open a new issue with a detailed description of the problem, including your local environment infos.
- For general questions or troubleshooting assistance, you can also reach out to the community through [GitHub discussions](https://github.com/lauvsong/LangCursor/discussions).

## Contribute
We welcome contributions to LangCursor! Your ideas and work can make a significant impact on the plugin. Here's how you can contribute:

- **Reporting Bugs**: If you find a bug, report it by creating a new issue on our [GitHub issues page](https://github.com/lauvsong/LangCursor/issues).
- **Suggesting Enhancements**: Have ideas for new features or improvements? Share them as feature requests on our issues page.
- **Submitting Pull Requests**: If you've developed a fix or an enhancement, submit a pull request with your changes. Please ensure your code follows the project's style and contribution guidelines.

Your contributions, whether big or small, play a crucial part in the growth and improvement of LangCursor. Join us in making this tool even better for everyone!

<!-- Plugin description end -->

## License
LangCursor is open-source and available under the [GPL v3.0 license](https://github.com/lauvsong/LangCursor/blob/main/LICENSE).

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
