package model.repo;

import model.Resource;
import model.ResourceCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceManager {

    private static Map<Long, Resource> resources = new HashMap<>();
    private static Long nextResource = 1L;

    // Adding resources
    public static void addResource(String title, String content, String link, ResourceCategory category) {
        Resource resource = new Resource(nextResource++, title, content, link, category);
        resources.put(resource.getId(),resource);
        System.out.println("         *** Resource added sucessfully!! *** ");
    }

    // Editing resources
    public static boolean editResource(Long id,String title, String content, String link, ResourceCategory category){
        Resource resource = resources.get(id);
        if(resource != null){
            resources.put(id, new Resource(nextResource++, title, content, link, category));
            return true;
        }
        return false;
    }

    // Deleting Resources
    public static Boolean deleteResource(Long id){
        return resources.remove(id) != null;
    }

    // Getting all resources
    public static List<Resource> getAllResources(){
        return new ArrayList<>(resources.values());
    }


    // Getting resources by category
    public static List<Resource> getByCategory(ResourceCategory category) {
        List<Resource> resourceList = new ArrayList<>();

        resourceList = resources
                .values()
                .stream()
                .filter(resource -> resource.getCategory().equals(category))
                .collect(Collectors.toList());

        return resourceList;
    }
}
