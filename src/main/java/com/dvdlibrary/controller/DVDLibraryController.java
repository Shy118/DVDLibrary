package com.dvdlibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvdlibrary.dao.DVDLibraryDao;
import com.dvdlibrary.dao.DVDLibraryDaoException;
import com.dvdlibrary.dto.DVD;
import com.dvdlibrary.ui.DVDLibraryView;

@Component(value = "control")
public class DVDLibraryController {
	@Autowired
	private DVDLibraryDao dao;
	@Autowired
	private DVDLibraryView view;

	public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
        	
			while (keepGoing) {

			    menuSelection = getMenuSelection();

			    switch (menuSelection) {
			    	case 1:
			        	createDVD();
			            break;
			        case 2:
			        	removeDVD();
			            break; 
			        case 3:
			        	editDVD();
			        	break;
			        case 4:
			        	listDVDs();
			            break;
			        case 5:
			        	viewDVDbyID();
			            break;
			        case 6:
			        	viewDVDbyTitle();
			        	break;
			        case 7:
			            keepGoing = false;
			            break;
			        default:
			        	unknownCommand();
			    }
			}
			
			exitMessage();
		} catch (DVDLibraryDaoException e) {
			view.displayErrorMessage(e.getMessage());
		}
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    

    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDvd = view.getNewDVDInfo();
        dao.addDVD(newDvd.getDvdId(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD removedDVD = dao.removeDVD(dvdId);
        view.displayRemoveResult(removedDVD);
    }
    
    private void editDVD() throws DVDLibraryDaoException {
    	view.displayEditBanner();
    	boolean continueEdit = true;
    	String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.getDVD(dvdId);
        view.displayDVD(dao.getDVD(dvdId));
    	if (dvd != null) {
    		while (continueEdit) {
    			int editChoice = view.printEditMenuAndGetSelection();
    			switch (editChoice) {
    				case 1:
    					String title = view.getEditTitleInfo();
    					dvd.setTitle(title);
    					dao.addDVD(dvdId, dvd);
    					break;
    				case 2:
    					String releaseDate = view.getEditReleaseDateInfo();
    					dvd.setReleaseDate(releaseDate);
    					dao.addDVD(dvdId, dvd);
    					break;
    				case 3:
    					String mPAA = view.getEditMPAAInfo();
    					dvd.setMPAA(mPAA);
    					dao.addDVD(dvdId, dvd);
    					break;
    				case 4:
    					String directorName = view.getEditDirectorNameInfo();
    					dvd.setDirectorName(directorName);
    					dao.addDVD(dvdId, dvd);
    					break;
    				case 5:
    					String studio = view.getEditStudioInfo();
    					dvd.setStudio(studio);
    					dao.addDVD(dvdId, dvd);
    					break;
    				case 6:
    					String rating = view.getEditRatingInfo();
    					dvd.setRating(rating);
    					dao.addDVD(dvdId, dvd);
    					break;
    				case 7:
    					title = view.getEditTitleInfo();
    					releaseDate = view.getEditReleaseDateInfo();
    					mPAA = view.getEditMPAAInfo();
    					directorName = view.getEditDirectorNameInfo();
    					studio = view.getEditStudioInfo();
    					rating = view.getEditRatingInfo();
    					dvd.setTitle(title);
    					dvd.setReleaseDate(releaseDate);
    					dvd.setMPAA(mPAA);
    					dvd.setDirectorName(directorName);
    					dvd.setStudio(studio);
    					dvd.setRating(rating);
    					dao.addDVD(dvdId, dvd);
    					break;
    				case 8:
    					continueEdit = false;
			            break;
    				default:
    					unknownCommand();
    			}
    		}
    	}
    }
    
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVDbyID() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.getDVD(dvdId);
        view.displayDVD(dvd);
    }
    
    private void viewDVDbyTitle() throws DVDLibraryDaoException {
    	view.displayDisplayDVDByTitleBanner();
    	String dvdTitle = view.getDVDTitleChoice();
        List<DVD> dvdList = dao.getAllDVDsByTitle(dvdTitle);
        view.displayDVDList(dvdList);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
