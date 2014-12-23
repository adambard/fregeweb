package fregeweb;

import java.util.Iterator;
import java.util.Map;

import clojure.lang.IMapEntry;
import clojure.lang.PersistentHashMap;
import clojure.lang.IPersistentCollection;
import clojure.lang.IPersistentMap;
import clojure.lang.APersistentMap;
import clojure.lang.ISeq;


/** A polite and pure hash map delegating to Clojure's PersistentHashMap, for
 use with Frege */
public class ImmutableHashMap extends APersistentMap {
  private final IPersistentMap delegate;

  // Frege-friendly constructors

  public ImmutableHashMap(IPersistentMap delegate){
    this.delegate = delegate;
  }

  public static ImmutableHashMap create(){
    return new ImmutableHashMap(PersistentHashMap.EMPTY);
  }

  // Expose delegate interfaces

  //// IPersistentMap

  public ImmutableHashMap assoc(Object key, Object value){
    return new ImmutableHashMap(this.delegate.assoc(key, value));
  }

  public ImmutableHashMap assocEx(Object key, Object value){
    return new ImmutableHashMap(this.delegate.assocEx(key, value));
  }

  public IPersistentMap without(Object key){
    return new ImmutableHashMap(this.delegate.without(key));
  }

  public String stringAt(Object key, String defaultVal){
    return this.valAt(key, defaultVal).toString();
  }

  public Object valAt(Object key, Object defaultVal){
    Object val = this.valAt(key);
    if(val == null){
      return defaultVal;
    }
    return val;
  }

  public Object valAt(Object key){ return this.delegate.valAt(key); }

  //// Iterable

  public Iterator<Object> iterator(){ return this.delegate.iterator(); }

  //// Associative

  public IMapEntry entryAt(Object key){ return this.delegate.entryAt(key); }
  public boolean containsKey(Object key){ return this.delegate.containsKey(key); }

  //// Collection maybe
  public boolean equiv(Object other){ return this.delegate.equiv(other); }
  public IPersistentCollection empty(){ return this.delegate.empty(); }
  public IPersistentCollection cons(Object o){ return this.delegate.cons(o); }
  public int count(){ return this.delegate.count(); }

  //// Seqable

  public ISeq seq(){ return this.delegate.seq(); }
}
