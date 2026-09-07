package rafael.alcocer.caldera.spring.aop.business.logic;

public interface Cache {

    void setCacheSize(int size);

    void setCacheTime(long time);
    
    void setMessage(Message message);
    
    Message getMessage();
    
    void throwRuntimeException();
}
