# Zumo

Zumo is an expense sharing application for mobile platforms. 
It allows users to create pools, add expenses, and settle up with friends.

Outline:
* run natively on both mobile platforms
* authentication through Google/Apple
* Clojure(script) as the main language using https://github.com/vouch-opensource/krell
* Local storage with Sqlite, synchronized to [Turso](https://turso.tech/blog/turso-goes-mobile-with-official-ios-and-android-sdks)
  * either with [Expo Sqlite](https://docs.expo.dev/versions/latest/sdk/sqlite/) or with [Op-Sqlite](https://op-engineering.github.io/op-sqlite/docs/Libsql/start)
* Ideally running [Datascript](https://github.com/tonsky/datascript) on top of Sqlite
  * currently not supported in ClojureScript