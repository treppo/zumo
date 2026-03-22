(ns zumo.db
  (:require [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [hugsql.core :as hugsql]
            [hugsql.adapter.next-jdbc :as next-adapter])
  (:import [com.zaxxer.hikari HikariDataSource]))

(hugsql/set-adapter! (next-adapter/hugsql-adapter-next-jdbc))

;; Load SQL queries from resources/sql/queries.sql
(hugsql/def-db-fns "sql/queries.sql")

(defn datasource [jdbc-url]
  (connection/->pool HikariDataSource
                     {:jdbcUrl jdbc-url
                      :maximumPoolSize 10}))
