package com.example.app_with_api_call;

public class CityInfo {
    private String city;
    private String state;
    private String country;
//    Right now, this is just a dummy class- a holder of info.
    public CityInfo(String body) {
        findInfo(body);
    }

    public String getCity() {
        return "City: " + city;
    }
    public String getState() {
        return "State: " + state;
    }
    public String getCountry() {
        return "Country: " + country;
    }
    public void findInfo(String body) {

//        I manually wrote this to just get the first city/state/country
//        that the API returns. It only looks at the first.
//        c keeps track of which character is being looked at, and i is the index.
        char c = body.charAt(0);
        int i = 0;
        StringBuilder cityReal = new StringBuilder();
        StringBuilder state = new StringBuilder();
        StringBuilder country = new StringBuilder();

        while (c != ':') {
            i++;
            c = body.charAt(i);
        }
//                    Get CITY
        i ++;
        while (c != '"') {
            cityReal.append(c);
            i++;
            c = body.charAt(i);
        }
        i++;

        while (c != ':') {
            i++;
            c = body.charAt(i);
        }
//                    Get STATE
        i ++;
        while (c != '"') {
            state.append(c);
            i++;
            c = body.charAt(i);
        }
        while (c != ':') {
            i++;
            c = body.charAt(i);
        }
//                    get COUNTRY
        i ++;
        while (c != '"') {
            country.append(c);
            i++;
            c = body.charAt(i);
        }
//        There is a ':' at the beginning of each string. This is a quick fix.
        cityReal.delete(0, 1);
        state.delete(0, 1);
        country.delete(0, 1);
        this.city = cityReal.toString();
        this.state = state.toString();
        this.country = country.toString();
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
