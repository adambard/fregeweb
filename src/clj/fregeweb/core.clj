(ns fregeweb.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [org.httpkit.server :refer [run-server]])
  (:import [fregeweb Handlers Handlers$TRequest Handlers$TResponse ImmutableHashMap]
           
           )
  )


(defroutes myapp
  (GET "/" [] "Hello World"))


(def handler
  (-> myapp
      (wrap-defaults site-defaults)))

(defn response-to-map [resp]
  {:body (Handlers$TResponse/body resp)
   :status (Handlers$TResponse/status resp)
   :headers (Handlers$TResponse/headers resp)
   }
  )



(defn -main []
  (run-server handler {:port 5000}))

(comment
  (-main)
  
  (require '[clojure.reflect :as r])
  (require '[clojure.pprint :refer [pprint]])
  (import fregeweb.Handlers$TResponse)
  (let [req (Handlers/make_request "hi" (ImmutableHashMap/create "")
                                               "name" "Frank" )
        resp (Handlers/index req)]
    (response-to-map resp)
    )
  )
