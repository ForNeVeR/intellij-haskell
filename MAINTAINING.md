<!--
SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>

SPDX-License-Identifier: Apache-2.0
-->

Maintaining
===========

Release
-------
To release a new version, follow these steps.

1. Update the **License** section in the `README.md`, if required.
2. Choose the new version according to [Semantic Versioning][semver]. It should consist of three numbers (i.e. `1.0.0`).
3. Change the `pluginVersion` property of the `gradle.properties`.
4. Make sure there's a properly formed version entry in the `CHANGELOG.md`.
5. Merge these changes via a PR.
6. Push a tag named `v<VERSION>` to GitHub.

The new release will be published automatically.

Update Token
------------
To update the token used for publishing the plugin, follow these steps.

1. Go to [the Marketplace token update section][marketplace.tokens].
2. Drop the old token called `github.haskeletor`.
3. Create a new one with the same name.
4. Go to [the GitHub repository's Secrets page][github.secrets].
5. Update the `PUBLISH_TOKEN` with the new value.

Rotate the Dependency Update Token
----------------------------------
This project uses a special GitHub application to manage the IntelliJ-based dependencies, as documented in [intellij-updater][].

To update the token:
1. Go to the [application settings][github.apps.intellij-updater].
2. Generate a new private key.
3. Copy the **App ID** to the `IJ_UPDATER_APP_ID` variable in [GitHub Actions secrets][github.secrets].
4. Copy the new private key to the `IJ_UPDATER_PRIVATE_KEY` variable in [GitHub Actions secrets][github.secrets].

[github.apps.intellij-updater]: https://github.com/settings/apps/intellij-updater
[github.secrets]: https://github.com/ForNeVeR/haskeletor/settings/secrets/actions
[intellij-updater]: https://github.com/ForNeVeR/intellij-updater
[marketplace.tokens]: https://plugins.jetbrains.com/author/me/tokens
[semver]: https://semver.org/spec/v2.0.0.html
