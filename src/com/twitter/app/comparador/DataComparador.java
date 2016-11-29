package com.twitter.app.comparador;

import java.util.Comparator;

import com.twitter.app.entity.Tweet;

public class DataComparador implements Comparator<Tweet> {

	@Override
	public int compare(Tweet tweet1, Tweet tweet2) {
		return tweet1.getData().compareTo(tweet2.getData());
	}

}
