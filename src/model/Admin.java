package model;

import repo.ResourceManager;

import java.util.List;

public class Admin extends User{

    // Creating admin user
    public Admin(Long userId, String name, String email, String password) {
        super(userId, name, email, password, Role.ADMIN);
    }

    // Adding Resources
    public void addResource(String title, String content, String link, ResourceCategory category){
        ResourceManager.addResource(title, content, link, category);
    }

    // Editing Resources
    public Boolean editResource(Long id, String title, String content, String link, ResourceCategory category){
        return ResourceManager.editResource(id, title, content, link, category);
    }


    // Deleting Resources
    public Boolean deleteResource(Long id){
        return ResourceManager.deleteResource(id);
    }

    public List<Resource> getAllResources(){
        return ResourceManager.getAllResources();
    }

    public List<Resource> getAllResourcesByCategory(ResourceCategory category){
        return ResourceManager.getByCategory(category);
    }
}
