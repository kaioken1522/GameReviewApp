package com.example.gamereview;


    public class ReadReviewsPageModal {

        String email;
        String review;
        String game;
        byte[] profilePic;

        public ReadReviewsPageModal(String email, String review, String game, byte[] profilePic) {
            this.email = email;
            this.review = review;
            this.game = game;
            this.profilePic = profilePic;
        }

        public String getEmail() {
            return email;
        }

        public String getReview() {
            return review;
        }

        public String getGame() {
            return game;
        }

        public byte[] getProfilePic() {
            return profilePic;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public void setProfilePic(byte[] profilePic) {
            this.profilePic = profilePic;
        }
    }

