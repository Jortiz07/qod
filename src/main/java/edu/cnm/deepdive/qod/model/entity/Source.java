package edu.cnm.deepdive.qod.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.qod.view.FlatQuote;
import edu.cnm.deepdive.qod.view.FlatSource;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(
    indexes = {
        @Index(columnList = "created")
    }
)
public class Source implements FlatSource {

  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "source_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updated;

  @NonNull
  @Column(length = 1024, nullable = false, unique = true)
  private String name;

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "source",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JsonSerialize(contentAs = FlatQuote.class)
  private Set<Quote> quotes = new LinkedHashSet<>();

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public Date getCreated() {
    return created;
  }

  @Override
  public Date getUpdated() {
    return updated;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }
  // TODO Update hash code

  public Set<Quote> getQuotes() {
    return quotes;
  }

  @Override
  public int hashCode() {
    return 31 * id.hashCode() + name.hashCode();
    // TODO Use pre-computed hash code.
  }

  @Override
  public boolean equals(Object obj) {
    boolean result = false;
    if (obj == this) {
      result = true;
    } else if (obj instanceof  Source && obj.hashCode() == hashCode()) {
      Source other = (Source) obj;
      result = id.equals(other.id) && name.equals(other.name);
    }
    return result;
  }

}