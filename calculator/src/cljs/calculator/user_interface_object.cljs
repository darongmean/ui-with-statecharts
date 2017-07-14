(ns calculator.user-interface-object
  (:require [calculator.control-object :as control-object]
            [rum.core :as rum]))


(enable-console-print!)


(rum/defc main-screen < rum/reactive [*app-state]
  (let [{:keys [state result num1 num2] :as st} (rum/react *app-state)]
    [:div
     [:h1 "Calculator"]
     [:table
      [:tbody
       [:tr
        [:td {:colSpan "5"} (condp = state
                              control-object/start-state result
                              control-object/operand2-state num2
                              num1)]]
       [:tr
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 7))} "7"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 8))} "8"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 9))} "9"]]
        [:td [:button "C"]]
        [:td [:button "CE"]]]
       [:tr
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 4))} "4"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 5))} "5"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 6))} "6"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/operator +))} "+"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/operator -))} "-"]]]
       [:tr
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 1))} "1"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 2))} "2"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/num 3))} "3"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/operator *))} "x"]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/operator /))} "/"]]]
       [:tr
        [:td {:colSpan 2} [:button {:on-click #(control-object/dispatch *app-state (control-object/num 0))} "0"]]
        [:td [:button "."]]
        [:td [:button {:on-click #(control-object/dispatch *app-state (control-object/equal))} "="]]
        [:td [:button "%"]]]]]
     [:h4 "st"]
     [:div (str st)]]))


(defn render [*app-state]
  (control-object/init-state *app-state)
  (rum/mount (main-screen *app-state) (. js/document (getElementById "app"))))

