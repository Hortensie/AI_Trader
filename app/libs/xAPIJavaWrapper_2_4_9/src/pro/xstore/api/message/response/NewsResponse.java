package pro.xstore.api.message.response;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.NewsTopicRecord;

public class NewsResponse extends BaseResponse {

    private List<NewsTopicRecord> newsTopicRecords = new LinkedList<NewsTopicRecord>();

    @SuppressWarnings("rawtypes")
    public NewsResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONArray arr = (JSONArray) this.getReturnData();
        for (Iterator it = arr.iterator(); it.hasNext();) {
            JSONObject e = (JSONObject) it.next();
            NewsTopicRecord record = new NewsTopicRecord();
            record.setFieldsFromJSONObject(e);
            newsTopicRecords.add(record);
        }
    }

    public List<NewsTopicRecord> getNewsTopicRecords() {
        return newsTopicRecords;
    }

    @Override
    public String toString() {
        return "NewsResponse{" + "newsTopicRecords=" + newsTopicRecords + '}';
    }
}
