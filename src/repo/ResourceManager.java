package repo;

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

    static {
        // Financial Tips
        resources.put(nextResource, new Resource(
                nextResource++,
                "Save Before You Spend",
                "Set aside at least 20% of your income for savings before making any purchases.",
                null,
                ResourceCategory.FINANCIAL_TIP
        ));

        resources.put(nextResource, new Resource(
                nextResource++,
                "Emergency Fund Importance",
                "Build an emergency fund covering at least 3–6 months of expenses to stay financially secure.",
                null,
                ResourceCategory.FINANCIAL_TIP
        ));

        // Banking Definitions
        resources.put(nextResource, new Resource(
                nextResource++,
                "Credit Card",
                "A card issued by banks that lets you borrow money up to a certain limit for purchases, with interest if not repaid in time.",
                null,
                ResourceCategory.BANKING_DEFINITION
        ));

        resources.put(nextResource, new Resource(
                nextResource++,
                "Overdraft",
                "When you withdraw more money than you have in your bank account, the bank covers the difference but may charge fees.",
                null,
                ResourceCategory.BANKING_DEFINITION
        ));

        resources.put(nextResource, new Resource(
                nextResource++,
                "APR",
                "Annual Percentage Rate – the yearly cost of borrowing on a credit card or loan, including fees and interest.",
                null,
                ResourceCategory.BANKING_DEFINITION
        ));

        // Learning Resources
        resources.put(nextResource, new Resource(
                nextResource++,
                "Beginner’s Guide to Budgeting",
                "Learn how to create a simple monthly budget using the 50/30/20 rule: 50% needs, 30% wants, 20% savings.",
                "https://www.investopedia.com/budgeting-4427788",
                ResourceCategory.LEARNING_RESOURCE
        ));

        resources.put(nextResource, new Resource(
                nextResource++,
                "Understanding Credit Scores",
                "Your credit score affects loan approvals and interest rates. Learn tips to improve your score.",
                "https://www.experian.com/blogs/news/2018/01/what-is-a-credit-score/",
                ResourceCategory.LEARNING_RESOURCE
        ));

        resources.put(nextResource, new Resource(
                nextResource++,
                "Financial Literacy Video",
                "Watch this short video explaining how compound interest works and why starting early matters.",
                "https://www.youtube.com/watch?v=E1Zvsw6VjdM",
                ResourceCategory.LEARNING_RESOURCE
        ));
    }

    // Adding resources
    public static void addResource(String title, String content, String link, ResourceCategory category) {
        Resource resource = new Resource(nextResource++, title, content, link, category);
        resources.put(resource.getId(),resource);
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
