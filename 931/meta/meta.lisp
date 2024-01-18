;; (defclass point ()
;;   (x y z))

;; (setf p (make-instance 'point))

;; (type-of p)

;; (setf (slot-value p 'x) 33)

;; (slot-value p 'x)

(defclass point (standard-object)
  ((x :initform 1
      :initarg :x
      :accessor getx
      )
   (y :initform 2 :initarg :y :accessor gety)
   (z :accessor getz :initarg :z :allocation :instance)))


(defclass person ()
  ((name :initarg :name :accessor name)
   (species
    :initform 'homo-sapiens
    :accessor species
    :allocation :class)))

(defclass child (person)
  ((can-walk-p :accessor can-walk-p :initform t)))

(defgeneric toString (obj)
  (:documentation "say hello to an object"))

(defmethod toString ((p person))
  (format t "Hello ~a !" (name p)))

(defmethod toString ((c child))
  (call-next-method c)
  (format t " young friend! ~&"))


(setf p (make-instance 'person :name "toto"))
(setf c (make-instance 'child :name "tata"))



(defclass memo-class (standard-class)
  ((list-instances :initform nil :accessor get-instances)) (:metaclass standard-class))

(defmethod make-instance ((mc memo-class) &rest args)
  (let ((newI (call-next-method)))
    (setf (get-instances mc)
          (cons newI (get-instances mc)))
    newI))

(defclass pile (standard-object)
  ((cap :initarg :cap :accessor get-cap)
   (content :initform '() :accessor get-content)
   (index :initform 0))
  (:metaclass memo-class))
