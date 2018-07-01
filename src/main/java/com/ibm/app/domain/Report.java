package com.ibm.app.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.ibm.app.domain.enumeration.STATUS;

import com.ibm.app.domain.enumeration.REWORK;

/**
 * A Report.
 */
@Entity
@Table(name = "report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tsm")
    private String tsm;

    @Column(name = "domain")
    private String domain;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "server")
    private String server;

    @Column(name = "datetimefalla")
    private ZonedDateTime datetimefalla;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private STATUS status;

    @Enumerated(EnumType.STRING)
    @Column(name = "rework")
    private REWORK rework;

    @Column(name = "error")
    private String error;

    @Column(name = "code")
    private String code;

    @Column(name = "datetimereworkini")
    private ZonedDateTime datetimereworkini;

    @Column(name = "datetimereworkfin")
    private ZonedDateTime datetimereworkfin;

    @Column(name = "ticket")
    private String ticket;

    @Column(name = "jhi_comment")
    private String comment;

    @Column(name = "jhi_user")
    private String user;

    @Column(name = "act")
    private String act;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTsm() {
        return tsm;
    }

    public Report tsm(String tsm) {
        this.tsm = tsm;
        return this;
    }

    public void setTsm(String tsm) {
        this.tsm = tsm;
    }

    public String getDomain() {
        return domain;
    }

    public Report domain(String domain) {
        this.domain = domain;
        return this;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSchedule() {
        return schedule;
    }

    public Report schedule(String schedule) {
        this.schedule = schedule;
        return this;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getServer() {
        return server;
    }

    public Report server(String server) {
        this.server = server;
        return this;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public ZonedDateTime getDatetimefalla() {
        return datetimefalla;
    }

    public Report datetimefalla(ZonedDateTime datetimefalla) {
        this.datetimefalla = datetimefalla;
        return this;
    }

    public void setDatetimefalla(ZonedDateTime datetimefalla) {
        this.datetimefalla = datetimefalla;
    }

    public STATUS getStatus() {
        return status;
    }

    public Report status(STATUS status) {
        this.status = status;
        return this;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public REWORK getRework() {
        return rework;
    }

    public Report rework(REWORK rework) {
        this.rework = rework;
        return this;
    }

    public void setRework(REWORK rework) {
        this.rework = rework;
    }

    public String getError() {
        return error;
    }

    public Report error(String error) {
        this.error = error;
        return this;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public Report code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ZonedDateTime getDatetimereworkini() {
        return datetimereworkini;
    }

    public Report datetimereworkini(ZonedDateTime datetimereworkini) {
        this.datetimereworkini = datetimereworkini;
        return this;
    }

    public void setDatetimereworkini(ZonedDateTime datetimereworkini) {
        this.datetimereworkini = datetimereworkini;
    }

    public ZonedDateTime getDatetimereworkfin() {
        return datetimereworkfin;
    }

    public Report datetimereworkfin(ZonedDateTime datetimereworkfin) {
        this.datetimereworkfin = datetimereworkfin;
        return this;
    }

    public void setDatetimereworkfin(ZonedDateTime datetimereworkfin) {
        this.datetimereworkfin = datetimereworkfin;
    }

    public String getTicket() {
        return ticket;
    }

    public Report ticket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getComment() {
        return comment;
    }

    public Report comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public Report user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAct() {
        return act;
    }

    public Report act(String act) {
        this.act = act;
        return this;
    }

    public void setAct(String act) {
        this.act = act;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        if (report.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), report.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Report{" +
            "id=" + getId() +
            ", tsm='" + getTsm() + "'" +
            ", domain='" + getDomain() + "'" +
            ", schedule='" + getSchedule() + "'" +
            ", server='" + getServer() + "'" +
            ", datetimefalla='" + getDatetimefalla() + "'" +
            ", status='" + getStatus() + "'" +
            ", rework='" + getRework() + "'" +
            ", error='" + getError() + "'" +
            ", code='" + getCode() + "'" +
            ", datetimereworkini='" + getDatetimereworkini() + "'" +
            ", datetimereworkfin='" + getDatetimereworkfin() + "'" +
            ", ticket='" + getTicket() + "'" +
            ", comment='" + getComment() + "'" +
            ", user='" + getUser() + "'" +
            ", act='" + getAct() + "'" +
            "}";
    }
}
