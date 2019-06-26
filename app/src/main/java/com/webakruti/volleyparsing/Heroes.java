package com.webakruti.volleyparsing;

import java.util.List;

/**
 * Created by DELL on 2/19/2019.
 */

public class Heroes {

        private List<Hero> heroes = null;

    public List<Hero> getHeroes() {
            return heroes;
        }

        public void setHeroes(List<Hero> heroes) {
            this.heroes = heroes;
        }


    public static class Hero {

        public Hero() {
        }

        private String name;

        private String imageurl;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

    }
}
