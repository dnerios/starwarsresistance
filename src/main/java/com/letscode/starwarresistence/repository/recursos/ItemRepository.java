package com.letscode.starwarresistence.repository.recursos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.starwarresistence.domain.recursos.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
