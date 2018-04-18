package com.sg.webservice.organization;

import com.sg.commandmodel.organization.createorg.CreateOrgCmdModel;
import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.dto.Person;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.service.PersonService;
import com.sg.util.PagingUtil;
import com.sg.viewmodel.organization.createlistorg.CreateListOrgViewModel;
import com.sg.viewmodel.organization.createlistorg.LocationViewModel;
import com.sg.viewmodel.organization.createlistorg.OrgViewModel;
import com.sg.webservices.exception.RelationshipFoundException;
import com.sg.webservices.organization.CreateListOrgWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreateListOrgWebServiceImpl implements CreateListOrgWebService {

    @Inject
    OrganizationService organizationService;

    @Inject
    LocationService locationService;

    @Inject
    PersonService personService;

    private List<OrgViewModel> translateOrgs(List<Organization> orgs){
        List<OrgViewModel> orgViewModels = new ArrayList<>();

        for (Organization org : orgs){
            OrgViewModel orgViewModel = new OrgViewModel();
            orgViewModel.setId(org.getId());
            orgViewModel.setName(org.getName());
            orgViewModels.add(orgViewModel);
        }

        return orgViewModels;
    }

    private List<LocationViewModel> translateLocations(List<Location> locations){
        List<LocationViewModel> locationViewModels = new ArrayList<>();

        for(Location location : locations){
            LocationViewModel locationViewModel = new LocationViewModel();
            locationViewModel.setId(location.getId());
            locationViewModel.setName(location.getName());
            locationViewModels.add(locationViewModel);
        }

        return locationViewModels;
    }

    @Override
    public CreateListOrgViewModel getCreateListOrgViewModel(Integer limit, Integer offset, Integer numPagesToShow) {

        if(limit == null) limit = 5;
        if(offset == null) offset = 0;
        if(numPagesToShow == null) numPagesToShow = 5;

        // instantiate
        CreateListOrgViewModel createListOrgViewModel = new CreateListOrgViewModel();
        CreateOrgCmdModel createOrgCmdModel = new CreateOrgCmdModel();

        // look stuff up
        List<Organization> orgs = organizationService.retrieveAllOrganizations(limit, offset);
        List<Location> locations = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);

        // calculate page info
        Integer currentPage = PagingUtil.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtil.getPageNumbers(currentPage, numPagesToShow);

        // put stuff
        createListOrgViewModel.setOrganizations(translateOrgs(orgs));
        createListOrgViewModel.setLocations(translateLocations(locations));
        createListOrgViewModel.setPageNumber(currentPage);
        createListOrgViewModel.setPageNumbers(pages);
        createListOrgViewModel.setCommandModel(createOrgCmdModel);

        return createListOrgViewModel;
    }

    @Override
    public Organization saveCreateOrgCmdModel(CreateOrgCmdModel createOrgCmdModel) {

        // instantiate
        Organization org = new Organization();

        // look up stuff
        Location locationToLookup = new Location();
        locationToLookup.setId(createOrgCmdModel.getLocationId());
        Location location = locationService.read(locationToLookup);

        // put stuff
        org.setName(createOrgCmdModel.getName());
        org.setDescription(createOrgCmdModel.getDescription());
        org.setLocation(location);

        // save stuff
        Organization createdOrg = organizationService.create(org);

        return createdOrg;
    }

    @Override
    public void deleteOrgCmdModel(Long aLong) throws RelationshipFoundException{
        //Instantiate
        Organization organization = new Organization();
        organization.setId(aLong);

        //Look stuff up
        List<Person> personList = personService.retrieveAllPersonsByOrg(organization, Integer.MAX_VALUE, Integer.valueOf(0));

        if(personList.size() == 0){
            organizationService.delete(organization);
        }else{
            throw new RelationshipFoundException(personList.size() + " people are associated with this organization. Action ignored.");
        }

    }
}
