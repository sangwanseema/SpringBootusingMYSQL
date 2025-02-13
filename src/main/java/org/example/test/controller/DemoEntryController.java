
/*package org.example.test.controller;

import org.example.test.entity.DemoEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// apply mapping on class
// url look like : localhost:8080/demo/health
//localhost:8080/demo/Entries
@RequestMapping("demo")
public class DemoEntryController {

     @GetMapping("health")
    public String healthCheck(){
       return "OK";
    }
    private Map<Long, DemoEntry> demoEntries=new HashMap<>();
     @GetMapping("Entries")
    public List<DemoEntry> getAll(){
   return new ArrayList<>(demoEntries.values());
    }

    @PostMapping("Entries")
public void createDemoEntry(@RequestBody DemoEntry myEntry)

    {
       // demoEntries.put(myEntry.getId(), myEntry);
    }

    @GetMapping("getEntryById/{myid}")
    public DemoEntry getDemoEntryById(@PathVariable Long myid)
    {
        return demoEntries.get(myid);
    }
    @DeleteMapping("deleteById/{myId}")
    public boolean deleteEntry(@PathVariable Long myId)
    {
       // if(demoEntries.containsKey(myId)==false)
            return false;
      //  demoEntries.remove(myId);
       // return true;
    }
    @PutMapping("UpdateById/{myId}")
    public boolean UpdateEntryById(@PathVariable Long myId,@RequestBody DemoEntry myEntry)
    {
       // if(demoEntries.containsKey(myId)==false)
            return false;
       // demoEntries.put(myId,myEntry);
        //return true;
    }
}
*/