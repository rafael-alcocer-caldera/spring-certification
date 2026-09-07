package rafael.alcocer.caldera.spring.aop.business.logic;

public class CacheImpl implements Cache {

    private int size;
    private long time;
    private Message message;

    public void setCacheSize(int size) {
        this.size = size;
    }

    public void setCacheTime(long time) {
        this.time = time;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
    
    public void throwRuntimeException() {
        throw new RuntimeException("Something failed!!!");
    }
}
