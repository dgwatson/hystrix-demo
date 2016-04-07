package com.captechconsulting.blog.hystrixdemo.downstream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/downstream")
public class DownstreamController {

    private int responseSeconds;

    @RequestMapping(method = RequestMethod.GET)
    public String handleGet() {
        System.out.println("Waiting " + responseSeconds + " seconds");
        try {
            Thread.sleep(responseSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("I just waited " + responseSeconds + " seconds");
        return "I just waited " + responseSeconds + " seconds\n";
    }

    @RequestMapping(value = "/responseseconds/{responseSeconds}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void setResponseSeconds(@PathVariable("responseSeconds") Integer responseSeconds) {
        if (responseSeconds == null) {
            this.responseSeconds = 0;
        } else {
            this.responseSeconds = responseSeconds.intValue();
        }
        System.out.println("responseSeconds set to " + responseSeconds + " seconds");
    }
}



