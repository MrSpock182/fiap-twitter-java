package com.twitter.app.programa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.twitter.app.controller.TwitterFunc;
import com.twitter.app.entity.Tweet;
import com.twitter.app.util.Util;

import twitter4j.TwitterException;

public class Main {
	public static void main(String[] args) {

		/*
		 * try { TwitterFunc twitterFunc = new TwitterFunc();
		 * System.out.println("No dia " + "29-11-2016" + " foram " +
		 * twitterFunc.getQtdeRT("#java", "2016-12-01", "2016-12-02") +
		 * " tweets"); } catch (TwitterException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

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

				System.out.println("tweets por dia " + pesquisa);
				for (int i = 7; i > 0; --i) {
					TwitterFunc twitterFunc = new TwitterFunc();

					System.out.println("No dia "
							+ LocalDate.now().plusDays(-(i - 1)).toString()
							+ " foram "
							+ twitterFunc.getQtdeTweets(pesquisa, LocalDate
									.now().plusDays(-i).toString(), LocalDate
									.now().plusDays(-(i - 1)).toString())
							+ " tweets");
				}

				System.out.println('\n');
				System.out.println("retweets por dia " + pesquisa);
				for (int i = 7; i > 0; --i) {
					TwitterFunc twitterFunc = new TwitterFunc();
					System.out
							.println("No dia "
									+ LocalDate.now().plusDays(-(i - 1))
											.toString()
									+ " foram "
									+ twitterFunc.getQtdeRT(pesquisa, LocalDate
											.now().plusDays(-i).toString(),
											LocalDate.now().plusDays(-(i - 1))
													.toString()) + " tweets");

				}

				System.out.println('\n');
				System.out.println("favoritações por dia " + pesquisa);
				for (int i = 7; i > 0; --i) {
					TwitterFunc twitterFunc = new TwitterFunc();
					System.out.println("No dia "
							+ LocalDate.now().plusDays(-(i - 1)).toString()
							+ " foram "
							+ twitterFunc.getQtdeFavoritos(pesquisa, LocalDate
									.now().plusDays(-i).toString(), LocalDate
									.now().plusDays(-(i - 1)).toString())
							+ " tweets");
				}

				System.out.println('\n');

				System.out.println("nomes ordenados " + pesquisa);
				for (int i = 7; i > 0; --i) {
					List<Tweet> tweets = new TwitterFunc().getListOrderName(
							pesquisa, LocalDate.now().plusDays(-i).toString(),
							LocalDate.now().plusDays(-(i - 1)).toString());

					for (Tweet tweet : tweets) {
						System.out
								.println("Nome ordenados: " + tweet.getNome());
					}
				}

				System.out.println('\n');
				System.out.println("data ordenados");
				for (int i = 7; i > 0; --i) {
					List<Tweet> tweets = new TwitterFunc().getListOrderDate(
							pesquisa, LocalDate.now().plusDays(-i).toString(),
							LocalDate.now().plusDays(-(i - 1)).toString());

					for (Tweet tweet : tweets) {
						System.out.println("Data ordenados: "
								+ Util.convertDate(tweet.getData()));
					}
				}

			}

			System.out
					.println("Tweet postado com sucesso! ["
							+ new TwitterFunc()
									.envioDeTweet("@michelpf trabalho de integração Twitter4Java de @KleberDelongas e @EduAraujoDev")
							+ "].");

		} catch (TwitterException e) {
			e.printStackTrace();
		}

	}

}
