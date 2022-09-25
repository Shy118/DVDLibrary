package com.dvdlibrary.dao;

import java.util.List;

import com.dvdlibrary.dto.DVD;

public interface DVDLibraryDao {

	/**
     * Adds the given DVD to the library and associates it with the given DVD id.
     * If there is already a DVD associated with the given DVD id,
     * it will return that DVD object, otherwise it will
     * return null.
     *
     * @param dvdId id with which DVD is to be associated
     * @param DVD dvd to be added to the library
     * @return the DVD object previously associated with the given  
     * DVD id if it exists, null otherwise
	 * @throws DVDLibraryDaoException 
     */
    DVD addDVD(String dvdId, DVD dvd) throws DVDLibraryDaoException;

    /**
     * Removes from the library the DVD associated with the given id.
     * Returns the DVD object that is being removed or null if
     * there is no DVD associated with the given id
     *
     * @param dvdId id of DVD to be removed
     * @return DVD object that was removed or null if no DVD
     * was associated with the given student id
     * @throws DVDLibraryDaoException 
     */
    DVD removeDVD(String dvdId) throws DVDLibraryDaoException;
    
    /**
     * Returns a List of all DVDs in the library.
     *
     * @return List containing all DVDs in the library.
     * @throws DVDLibraryDaoException 
     */
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    /**
     * Returns a List of all DVDs in the library by title.
     *
     * @param String title of DVD to search for
     * @return List containing all DVDs in the library by title.
     * @throws DVDLibraryDaoException 
     */
    List<DVD> getAllDVDsByTitle(String title) throws DVDLibraryDaoException;
    
    /**
     * Returns the DVD object associated with the given DVD id.
     * Returns null if no such DVD exists
     *
     * @param dvdId ID of the movie to retrieve
     * @return the DVD object associated with the given DVD id,  
     * null if no such DVD exists
     * @throws DVDLibraryDaoException 
     */
    DVD getDVD(String dvdId) throws DVDLibraryDaoException;
     
} //interface
