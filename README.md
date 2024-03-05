# Kmplate-lib

Kmplate-lib is a template to easily create a new Kotlin Multiplaform library with Maven Central publishing configuration.

### 1. Create a webhook post messages on Slack

1. For Github Actions to post messages on Slack, you must create a new webhook URL by using the [Incoming Webhook](https://slack.com/apps/A0F7XDUAZ-incoming-webhooks) app.
2. Create a new [Github Actions secret](https://docs.github.com/en/actions/security-guides/encrypted-secrets) with name `SLACK_WEBHOOK_URL`, and copy paste the webhook created in the previous step as
   value of this secret.

### 2. Configure the Slack bot to post on Slack

We will configure 2 Slack bots to post message on Slack: one bot to check for outdated dependencies, and one bot for the build status.
To configure these 2 Slack bots, we need to create 3 [Github Actions variables](https://docs.github.com/en/actions/learn-github-actions/variables):

1. `SLACK_GITHUB_ACTIONS_CHANNEL_NAME`: the name of the Slack channel where Github Actions will post messages (ie. `myproject_build_status`).
2. `SLACK_GITHUB_ACTIONS_DEPENDENCY_UPDATES_ICON_URL`: the icon URL to be used as a profile picture for the "Dependency Updates" Slack bot.
3. `SLACK_GITHUB_ACTIONS_ICON_URL`: the icon URL to be used as a profile picture for the "Github Actions CI" Slack bot.

### 3. Configure publishing on Maven Central with Sonatype

Publishing the library is done via Github Actions, from the workflow `.github/workflows/publish.yml`, and will automatically publish a new version of the library to Maven Central, for every new
release created on Github.

- First, you need to create an account on Sonatype. Follow this guide: https://central.sonatype.org/publish/publish-guide/. You should end up with a **username**, a **password** and a **staging
  profile ID**.
- Once you have your account, you need to request the creation of your groupId (ie. 'com.mycompany.myname'). Create an issue on their Jira. Example: https://issues.sonatype.org/browse/OSSRH-97913.
- Then, create your secret key by following this guide: https://central.sonatype.org/publish/requirements/gpg/. You should end up with a **secret key**, a **secret key ID** and a **secret key password
  **.

To configure the publishing, we need to create 6 [Github Actions secrets](https://docs.github.com/en/actions/security-guides/encrypted-secrets):

1. `OSSRH_GPG_SECRET_KEY`: The value of the secret key created.
2. `OSSRH_GPG_SECRET_KEY_ID`: The ID of the secret key created (the last 16 digits).
3. `OSSRH_GPG_SECRET_KEY_PASSWORD`: The password of the secret key created.
4. `OSSRH_PASSWORD`: Your Sonatype account password.
5. `OSSRH_STAGING_PROFILE_ID`: Your Sonatype staging profile ID.
6. `OSSRH_USERNAME`: Your Sonatype account username.

### 4. Rename package name to your own

1. Open `buildSrc/src/main/kotlin/Dependencies.kt` and rename the following things:
    1. _Line 22_: Change `MyProject` object name to your own project name,
    2. _Line 23_: Change `com.tweener.changehere` package name to your own package name.
   3. _Line 30_: Change all the properties within the `object Maven` block to your own Maven Central publishing configuration.
2. Rename module `changehere` to the name of your library. This is the name that will be shown when published to Maven Central.
3. Open `settings.gradle.kts` and change `MyProjectName` on line 17 by your own project name.
4. Open `changehere/build.gradle.kts` (or `yourlibraryname/build.gradle.kts` if you renamed the module on step 2) and change `changehere` on line 65 with your own iOS framework name.
5. Rename packages name (`import` and `package`) in all existing files:
    1. Click on `Edit` > `Find` > `Replace in files`,
    2. In the first input field, type `com.tweener.changehere`,
    3. In the second input field, type your own package name,
    4. Click on `Replace all` button.
6. Replace `com/tweener/changehere` by your own directory path in the following directories:
    1. `changehere/src/commonMain/kotlin/com/tweener/changehere`
    2. `changehere/src/androidMain/kotlin/com/tweener/changehere`
    3. `changehere/src/iosMain/kotlin/com/tweener/changehere`

### 5. Rename Github Actions names

1. Open `.github/workflows/buildRelease.yml` and replace `Kmplate-lib` on lines 1, 60 and 72 by your own library name.
2. Open `.github/workflows/notify.yml` and replace `Kmplate-lib` on lines 21 and 33 by your own library name.
