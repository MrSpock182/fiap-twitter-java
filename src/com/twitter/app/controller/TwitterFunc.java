package com.twitter.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.twitter.app.comparador.DataComparador;
import com.twitter.app.comparador.NomeComparador;
import com.twitter.app.entity.Tweet;
import com.twitter.app.util.Constantes;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterFunc {

	/***
	 * Metodo de configuração das chaves do Twitter para utilizar os seus
	 * recursos.
	 * 
	 * @return
	 */

	private Twitter connTwitter() {
		AccessToken accessToken = new AccessToken(Constantes.token,
				Constantes.tokenSecret);
		ConfigurationBuilder builder = new ConfigurationBuilder();

		builder.setOAuthConsumerKey(Constantes.consumerKey);
		builder.setOAuthConsumerSecret(Constantes.consumerSecret);

		Configuration configuration = builder.build();
		TwitterFactory factory = new TwitterFactory(configuration);
		Twitter twitter = factory.getInstance();

		twitter.setOAuthAccessToken(accessToken);
		return twitter;
	}

	/***
	 * Metodo responsavel por calcular quantidade de tweets que uma palavra teve
	 * entre duas datas.
	 * 
	 * @param tweet
	 * @param since
	 * @param until
	 * @return Quantidade de tweets para determinada palavras
	 * @throws TwitterException
	 */
	public Integer getQtdeTweets(String tweet, String since, String until)
			throws TwitterException {
		Twitter twitter = connTwitter();
		Query query = new Query(tweet);

		query.since(since);
		query.until(until);

		QueryResult result;

		result = twitter.search(query);

		return result.getCount();
	}

	/***
	 * Metodo responsavel por calcular quantidade de retweets que um tweet teve
	 * entre duas datas.
	 * 
	 * @param tweet
	 * @param since
	 * @param until
	 * @return Quantidade de retweets para determinada tweet
	 * @throws TwitterException
	 */
	public Integer getQtdeRT(String tweet, String since, String until)
			throws TwitterException {
		Twitter twitter = connTwitter();
		Query query = new Query(tweet);
		Integer retorno = 0;

		query.setSince(since);
		query.setUntil(until);
		QueryResult result;

		result = twitter.search(query);

		while (result.hasNext()) {
			query = result.nextQuery();

			for (Status status : result.getTweets()) {
				retorno += status.getRetweetCount();
			}
			result = twitter.search(query);
		}

		return retorno;
	}

	/***
	 * Metodo responsavel por calcular quantidade de favoritos que um tweet teve
	 * entre duas datas.
	 * 
	 * @param tweet
	 * @param since
	 * @param until
	 * @return Quantidade de favoritos para determinada tweet
	 * @throws TwitterException
	 */
	public Integer getQtdeFavoritos(String tweet, String since, String until)
			throws TwitterException {
		Twitter twitter = connTwitter();
		Query query = new Query(tweet);
		Integer retorno = 0;

		query.setSince(since);
		query.setUntil(until);
		QueryResult result;

		result = twitter.search(query);

		while (result.hasNext()) {
			query = result.nextQuery();

			for (Status status : result.getTweets()) {
				retorno += status.getFavoriteCount();
			}
			result = twitter.search(query);
		}

		return retorno;
	}

	/***
	 * Metodo responsavel por retornar lista ordenada de nomes;
	 * 
	 * @param tweetQry, since, until
	 * @return
	 * @throws TwitterException
	 */
	public List<Tweet> getListOrderName(String tweetQry, String since, String until)
			throws TwitterException {
		List<Tweet> tweets = new ArrayList<Tweet>();
		Twitter twitter = connTwitter();
		Query query = new Query(tweetQry);
		
		query.setSince(since);
		query.setUntil(until);
		
		QueryResult result;

		result = twitter.search(query);

		while (result.hasNext()) {
			query = result.nextQuery();

			for (Status status : result.getTweets()) {
				Tweet tweet = new Tweet();
				tweet.setNome(status.getUser().getName().toString().toUpperCase());
				// status.getUser().getScreenName();

				tweets.add(tweet);
			}
			result = twitter.search(query);
		}

		NomeComparador nomeComparador = new NomeComparador();
		Collections.sort(tweets, nomeComparador);

		return tweets;
	}

	/***
	 * Metodo responsavel por retornar lista ordenada de datas;
	 * @param tweetQry, since, until
	 * @return
	 * @throws TwitterException
	 */
	public List<Tweet> getListOrderDate(String tweetQry, String since, String until)
			throws TwitterException {
		List<Tweet> tweets = new ArrayList<Tweet>();
		Twitter twitter = connTwitter();
		Query query = new Query(tweetQry);
		QueryResult result;

		result = twitter.search(query);

		while (result.hasNext()) {
			query = result.nextQuery();

			for (Status status : result.getTweets()) {
				Tweet tweet = new Tweet();
				tweet.setData(status.getCreatedAt());
				
				tweets.add(tweet);
			}
			result = twitter.search(query);
		}
		
		DataComparador dataComparador = new DataComparador();
		Collections.sort(tweets, dataComparador);

		return tweets;
	}

	/***
	 * Metodo para tweetar uma mensagem
	 * 
	 * @param mensagem
	 */
	public String envioDeTweet(String mensagem) throws TwitterException {
		Twitter twitter = connTwitter();
		Status status = twitter.updateStatus(mensagem);
		return status.getText();
	}

}
