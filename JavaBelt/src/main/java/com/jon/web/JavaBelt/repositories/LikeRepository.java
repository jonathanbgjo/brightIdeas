package com.jon.web.JavaBelt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jon.web.JavaBelt.models.Idea;
import com.jon.web.JavaBelt.models.Like;
import com.jon.web.JavaBelt.models.User;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long>{
	List<Like> findAll();

	List<Like> findAllByUser(User showUser);

}
