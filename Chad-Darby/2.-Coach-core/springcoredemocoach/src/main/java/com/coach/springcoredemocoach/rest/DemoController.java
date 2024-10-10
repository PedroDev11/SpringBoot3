package com.coach.springcoredemocoach.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coach.springcoredemocoach.common.Coach;

@RestController
public class DemoController {
    
    // Define a private field for the dependency
    private Coach myCoach;

    // CONSTRUCTOR INJECTION
    // Define a constructor for dependency injection
    // Autowired annotation tells Spring to inject a dependency
   @Autowired
    public DemoController(@Qualifier("swim") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }
    

    // SETTER INJECTION
    /*@Autowired
    public void setCoach(Coach theCoach) {
        myCoach = theCoach;
    } */

    // FIELD INJECTION -> NOT RECOMMEND BY SPRING.IO TEAM
    /* @Autowired
    private Coach myCoach; */

    // @QUALIFIER ANNOTATION 
    /*@Autowired // Annotation @Qualifier is to identify the bean that should be consumed.
    public DemoController(@Qualifier("baseballCoach") Coach theCoach) {
        myCoach = theCoach;
    }*/

    // @PRIMARY ANNOTATION
    /* Implementing the annotation @Primary on the Component class (TrackCoach) */
    /*@Autowired 
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }*/

    // @LAZY ANNOTATION
    /* Implementing @LAZY annotation to only create the components necessaries, that´s means it 
    is not initialized, it´s not injecting the component. 
    PROPERTIES: spring.main.lazy-initialization=true -> not initialize any component until 
    it´s required */
    /*@Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }*/

    /* SINGLETON -> By default
    All dependencies injections for the bean will reference the same bean 
    myCoach == anotherCoach -> TRUE
    */
    /* @Autowired 
    public DemoController(
                @Qualifier("cricketCoach") Coach theCoach,
                @Qualifier("cricketCoach") Coach theAnotherCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        
        // Make the appropriate assignments
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    } */

    /* PROTOTYPE -> by using @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Creates a new object instance for each injection
    myCoach == anotherCoach -> FLASE
    @Autowired 
    public DemoController(
        @Qualifier("cricketCoach") Coach theCoach,
        @Qualifier("cricketCoach") Coach theAnotherCoach) {
            System.out.println("In constructor: " + getClass().getSimpleName());
            
            // Make the appropriate assignments
            myCoach = theCoach;
            anotherCoach = theAnotherCoach;
        }
    */
        
    @GetMapping("/dailyworkout")
    public String getDailyWorkOut() {
        return myCoach.getDailyWorkOut();
    }
}
