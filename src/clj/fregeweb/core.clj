(ns fregeweb.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [org.httpkit.server :refer [run-server]])
  (:import [fregeweb Handlers
                     Handlers$TResponse
                     ImmutableHashMap]))

; Frege helper stuff

(defn map-to-request [req]
  (Handlers/make_request
    (:uri req)
    (ImmutableHashMap. (:params req))))

(defn response-to-map [resp]
  {:body (Handlers$TResponse/body resp)
   :status (Handlers$TResponse/status resp)
   :headers (Handlers$TResponse/headers resp)})

(defmacro frege-view [sym]
  `(fn [req#] (response-to-map (~sym (map-to-request req#)))))


; Routes

(defroutes myapp
  (GET "/" [] (frege-view Handlers/index))
  (GET "/test" req {:body "Hello World"
                    :status 200
                    :headers (hash-map "Content-Type" "text/html") })
  )


(def handler-settings
  ; Make sure params aren't keywordized!
  (assoc-in  site-defaults [:params :keywordize] false))

(def handler
  (-> myapp
      (wrap-defaults handler-settings)))

(defn -main []
  (run-server handler {:port 5000}))

(comment
  (def stop (-main))
  (stop)

  (prn site-defaults)
  
  
  (require '[clojure.reflect :as r])
  (require '[clojure.pprint :refer [pprint]])
  (import fregeweb.Handlers$TResponse)
  (let [req (Handlers/make_request "hi" (assoc (ImmutableHashMap/create "")
                                               "name" "Frank") )
        handlerFn #(Handlers/index %)
        resp (handlerFn req)]
    (response-to-map resp)
    ))
