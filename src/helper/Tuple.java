package helper;

public class Tuple<X, Y> {


    private X x;
        private Y y;

        public Tuple(X x, Y y){
            this.x = x;
            this.y = y;
        }

        public X getX(){
            return this.x;
        }

        public Y getY(){
            return this.y;
        }

        public void setX(X x) {
            this.x = x;
        }

        public void setY(Y y) {
            this.y = y;
        }

        public boolean equals(Object other){

            if (other == this){
                return true;
            }

            if (!(other instanceof Tuple <?, ?>)){
                return false;
            }

            Tuple <?, ?> otherT = (Tuple<?, ?>) other;
            return this.x == otherT.getX() && this.y == otherT.getY();
        }
}

