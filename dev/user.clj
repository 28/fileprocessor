(ns user)

(defn dev
  "Loads and switches to the dev namespace."
  []
  (require 'dev)
  (in-ns 'dev)
  :dev-loaded)
