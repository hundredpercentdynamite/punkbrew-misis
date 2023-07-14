package com.example.punkbrewery.entities;


import androidx.annotation.Nullable;

import java.util.Date;

public class RequestParams {
    public int abv_gt; //	number	Returns all beers with ABV greater than the supplied number
    public int abv_lt; //	number	Returns all beers with ABV less than the supplied number
    public int ibu_gt; //	number	Returns all beers with IBU greater than the supplied number
    public int ibu_lt; //	number	Returns all beers with IBU less than the supplied number
    public int ebc_gt; //	number	Returns all beers with EBC greater than the supplied number
    public int ebc_lt; //	number	Returns all beers with EBC less than the supplied number
    public String beer_name; //	string	Returns all beers matching the supplied name (this will match partial strings as well so e.g punk will return Punk IPA), if you need to add spaces just add an underscore (_).
    public String yeast; //	string	Returns all beers matching the supplied yeast name, this performs a fuzzy match, if you need to add spaces just add an underscore (_).
    public Date brewed_before; //	date	Returns all beers brewed before this date, the date format is mm-yyyy e.g 10-2011
    public Date brewed_after; //	date	Returns all beers brewed after this date, the date format is mm-yyyy e.g 10-2011
    public String hops; //	string	Returns all beers matching the supplied hops name, this performs a fuzzy match, if you need to add spaces just add an underscore (_).
    public String malt; //	string	Returns all beers matching the supplied malt name, this performs a fuzzy match, if you need to add spaces just add an underscore (_).
    public String food; //	string	Returns all beers matching the supplied food string, this performs a fuzzy match, if you need to add spaces just add an underscore (_).
    public String ids; //	string (id|id|...)	Returns all beers matching the supplied ID's. You can pass in multiple ID's by separating them with a | symbol.
}

enum Params {
    abv_gt,
    abv_lt,
    ibu_gt,
    ibu_lt,
    ebc_gt,
    ebc_lt,
    beer_name,
    yeast,
    brewed_before,
    brewed_after,
    hops,
    malt,
    food,
    ids
}
