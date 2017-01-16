/*package com.vaadin.polymer.demo.client.sampler.ai_trader;


import org.bigml.binding.AuthenticationException;
import org.bigml.binding.BigMLClient;
import org.json.simple.JSONObject;



public class neuroticNetwork {


    
    public void connectToBigMlAPI(BigMLClient bigMLClient)
    {
        try
        {
            bigMLClient = BigMLClient.getInstance();
            JSONObject source = bigMLClient.createSource("data/iris.csv", "My First Source", null);
            System.out.println(source.toJSONString());
            // Wait until source is ready
            while (!bigMLClient.sourceIsReady(source))
            {
                source = bigMLClient.getSource(source);
                System.out.println("Waiting for source to be ready");
            }
            System.out.println(source.toJSONString());
        }
        catch (AuthenticationException e)
        {
            e.printStackTrace();
        }
    }
}

*/