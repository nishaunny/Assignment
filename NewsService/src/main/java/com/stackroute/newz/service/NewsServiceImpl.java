package com.stackroute.newz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.util.exception.NewsNotFoundException;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */

@Service
public class NewsServiceImpl implements NewsService {

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	NewsRepository newsRepo;
	
	public NewsServiceImpl(NewsRepository newsRepository) {
		this.newsRepo = newsRepository;
	}

	/*
	 * This method should be used to save a new news.
	 */
	@Override
	public boolean addNews(News news) {
		List<News> newsList = new ArrayList<>();
		newsList.add(news);
		
		UserNews user = new UserNews();
		user.setUserId(news.getNewsSource().getNewsSourceCreatedBy());
		user.setNewslist(newsList);
		UserNews newUser = newsRepo.insert(user);
		if (newUser!= null) {
			return true;
		} else {
			return false;
		}
	}

	/* This method should be used to delete an existing news. */
	
	public boolean deleteNews(String userId, int newsId) {
		Optional<UserNews> userNewsExist = newsRepo.findById(userId);
		if (userNewsExist.isPresent()) {
			List<News> newsList = userNewsExist.get().getNewslist();
			     if(newsList.isEmpty()) {
			    	   return false;
			    	 
			     }else {
			    	   
			       Iterator<News> iterator = newsList.iterator();
			
			      while (iterator.hasNext()) {
				News news = iterator.next();
				if (news.getNewsId() == newsId) {
					 iterator.remove();
					userNewsExist.get().setNewslist(newsList);
					newsRepo.save(userNewsExist.get());
				   return true;
			}
		}
			return false;
			     }
		}else
			throw new NullPointerException("News not found");
	}

	/* This method should be used to delete all news for a  specific userId. */
	
	public boolean deleteAllNews(String userId) throws NewsNotFoundException {
		Optional<UserNews> userNews = newsRepo.findById(userId);
		if (userNews.isPresent()) {
			userNews.get().getNewslist().clear();
			return true;
		}else
	
		    throw new NewsNotFoundException("News Not Found");
	}

	/*
	 * This method should be used to update a existing news.
	 */

	public News updateNews(News news, int newsId, String userId) throws NewsNotFoundException {
		
		Optional<UserNews> userNews = newsRepo.findById(userId);
		if (userNews.isPresent()) {
			List<News> newsList = userNews.get().getNewslist();
			Iterator<News> iterator = newsList.iterator();
			while (iterator.hasNext()) {
				News existedNews = iterator.next();
				if (existedNews.getNewsId() == newsId) {
					existedNews.setAuthor(news.getAuthor());
					existedNews.setContent(news.getContent());
					existedNews.setDescription(news.getDescription());
					existedNews.setNewsSource(news.getNewsSource());
					existedNews.setPublishedAt();
					existedNews.setReminder(news.getReminder());
					existedNews.setTitle(news.getTitle());
					existedNews.setUrl(news.getUrl());
					existedNews.setUrlToImage(news.getUrlToImage());
					
					userNews.get().setNewslist(newsList);
					newsRepo.save(userNews.get());
					return existedNews;

				}else
				     throw new NewsNotFoundException("News Not Found");
			}
		}
			return null;
}

	/*
	 * This method should be used to get a news by newsId created by specific user
	 */

	public News getNewsByNewsId(String userId, int newsId) throws NewsNotFoundException {
		
		News  newsFound = null;
		Optional<UserNews> userNews = newsRepo.findById(userId);
		if (userNews.isPresent()) {
			List<News> newsList = userNews.get().getNewslist();
			  for(News newsObj : newsList) {
				     if(newsObj.getNewsId()==newsId) 
				    	 newsFound =  newsObj;
			  }
			  return newsFound;
		}else 
			throw new NewsNotFoundException("News Not Found");  
			
}

	/*
	 * This method should be used to get all news for a specific userId.
	 */

	public List<News> getAllNewsByUserId(String userId) {
		Optional<UserNews> userNews = newsRepo.findById(userId);
		if (userNews.isPresent()) {
			return userNews.get().getNewslist();
		}
		return null;
	}

}
