package com.pankul.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankul.models.HotelsModel;

/**
 * Repository interface for accessing hotel data from the database.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations for
 * {@link HotelsModel} entities. Includes a custom method to find hotels by city
 * name, ignoring case sensitivity.
 * </p>
 *
 * @author pankulbindal
 */
@Repository
public interface HotelsRepo extends JpaRepository<HotelsModel, Long> {

	/**
	 * Retrieves a list of hotels located in the specified city, case-insensitive.
	 *
	 * @param city the name of the city to search hotels in
	 * @return a list of {@link HotelsModel} entities in the given city
	 */
	List<HotelsModel> findByCityIgnoreCase(String city);

}
