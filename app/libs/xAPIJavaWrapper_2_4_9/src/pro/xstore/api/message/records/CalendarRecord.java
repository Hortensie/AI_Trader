package pro.xstore.api.message.records;

import org.json.simple.JSONObject;

public class CalendarRecord implements BaseResponseRecord {

	private String country;
	private String current;
	private String forecast;
	private String impact;
	private String period;
	private String previous;
	private long time;
	private String title;

    public CalendarRecord() {}

	@Override
    public void setFieldsFromJSONObject(JSONObject e) {
        this.setCountry((String) e.get("country"));
        this.setCurrent((String) e.get("current"));
        this.setForecast((String) e.get("forecast"));
        this.setImpact((String) e.get("impact"));
        this.setPeriod((String) e.get("period"));
        this.setPrevious((String) e.get("previous"));
        this.setTitle((String) e.get("title"));
        this.setTime((Long) e.get("time"));
    }

    @Override
    public String toString() {
        return "CalendarRecord{" + "time=" + time + ", title=" + title + ", country=" + country + ", previous=" + previous + ", impact=" + impact + ", forecast=" + forecast + ", current=" + current + '}';
    }

    public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}