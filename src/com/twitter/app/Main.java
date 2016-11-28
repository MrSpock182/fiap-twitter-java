package com.twitter.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import twitter4j.TwitterException;

public class Main {
	public static void main(String[] args) {

		List<String> pesquisas = new ArrayList<>();
		pesquisas.add("#java");
		pesquisas.add("#jvm");
		pesquisas.add("#javaone");
		pesquisas.add("#openjdk");
		pesquisas.add("#java7");
		pesquisas.add("#java8");
		pesquisas.add("#java9");

		try {

			for (String pesquisa : pesquisas) {
				System.out.println("tweets por dia");
				for (int i = 7; i > 0; --i) {
					TwitterFunc twitterFunc = new TwitterFunc();
					System.out.println("No dia "
							+ LocalDate.now().plusDays(-(i - 1)).toString()
							+ " foram "
							+ twitterFunc.getQtdeTweets(pesquisa, LocalDate.now()
									.plusDays(-i).toString(), LocalDate.now()
									.plusDays(-(i - 1)).toString()) + " tweets");

				}

				System.out.println('\n');
				System.out.println("retweets por dia");
				for (int i = 7; i > 0; --i) {
					TwitterFunc twitterFunc = new TwitterFunc();
					System.out.println("No dia "
							+ LocalDate.now().plusDays(-(i - 1)).toString()
							+ " foram "
							+ twitterFunc.getQtdeRT(pesquisa, LocalDate.now()
									.plusDays(-i).toString(), LocalDate.now()
									.plusDays(-(i - 1)).toString()) + " tweets");

				}

				System.out.println('\n');
				System.out.println("favoritações por dia");
				for (int i = 7; i > 0; --i) {
					TwitterFunc twitterFunc = new TwitterFunc();
					System.out.println("No dia "
							+ LocalDate.now().plusDays(-(i - 1)).toString()
							+ " foram "
							+ twitterFunc.getQtdeFavoritos(pesquisa, LocalDate.now()
									.plusDays(-i).toString(), LocalDate.now()
									.plusDays(-(i - 1)).toString()) + " tweets");

				}
			}
			
			System.out.println("Tweet postado com sucesso! [" + new TwitterFunc().envioDeTweet("@EduAraujoDev Você tem que estudar mais Java.")  + "].");
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}

	}

}
