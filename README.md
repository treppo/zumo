# Zumo

Zumo is an expense sharing application for mobile platforms. 
It allows users to create pools, add expenses, and settle up with friends.

Outline:
* run natively on both mobile platforms
* authentication through Google/Apple
* Clojure(script) as the main language using [Helix](https://github.com/lilactown/helix/blob/master/docs/react-native.md)
* Local storage with Sqlite, synchronized to [Turso](https://turso.tech/blog/turso-goes-mobile-with-official-ios-and-android-sdks)
  * either with [Expo Sqlite](https://docs.expo.dev/versions/latest/sdk/sqlite/) or with [Op-Sqlite](https://op-engineering.github.io/op-sqlite/docs/Libsql/start)
* Ideally running [Datascript](https://github.com/tonsky/datascript) on top of Sqlite
  * currently not supported in ClojureScript
* Consider libraries:
  * i18n (https://github.com/tonsky/tongue)
  * sortable type id with (https://github.com/jetify-com/typeid-js) or (https://github.com/tonsky/compact-uuids)
  * drag and drop (https://github.com/tonsky/cljs-drag-n-drop)
  * Tailwind for mobile (https://www.nativewind.dev)