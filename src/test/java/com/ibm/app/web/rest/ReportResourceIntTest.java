package com.ibm.app.web.rest;

import com.ibm.app.ArgosApp;

import com.ibm.app.domain.Report;
import com.ibm.app.repository.ReportRepository;
import com.ibm.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.ibm.app.web.rest.TestUtil.sameInstant;
import static com.ibm.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ibm.app.domain.enumeration.STATUS;
import com.ibm.app.domain.enumeration.REWORK;
/**
 * Test class for the ReportResource REST controller.
 *
 * @see ReportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArgosApp.class)
public class ReportResourceIntTest {

    private static final String DEFAULT_TSM = "AAAAAAAAAA";
    private static final String UPDATED_TSM = "BBBBBBBBBB";

    private static final String DEFAULT_DOMAIN = "AAAAAAAAAA";
    private static final String UPDATED_DOMAIN = "BBBBBBBBBB";

    private static final String DEFAULT_SCHEDULE = "AAAAAAAAAA";
    private static final String UPDATED_SCHEDULE = "BBBBBBBBBB";

    private static final String DEFAULT_SERVER = "AAAAAAAAAA";
    private static final String UPDATED_SERVER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATETIMEFALLA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATETIMEFALLA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final STATUS DEFAULT_STATUS = STATUS.PENDIENTE;
    private static final STATUS UPDATED_STATUS = STATUS.ATENDIDO;

    private static final REWORK DEFAULT_REWORK = REWORK.NO;
    private static final REWORK UPDATED_REWORK = REWORK.SI;

    private static final String DEFAULT_ERROR = "AAAAAAAAAA";
    private static final String UPDATED_ERROR = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATETIMEREWORKINI = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATETIMEREWORKINI = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATETIMEREWORKFIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATETIMEREWORKFIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TICKET = "AAAAAAAAAA";
    private static final String UPDATED_TICKET = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_ACT = "AAAAAAAAAA";
    private static final String UPDATED_ACT = "BBBBBBBBBB";

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReportMockMvc;

    private Report report;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReportResource reportResource = new ReportResource(reportRepository);
        this.restReportMockMvc = MockMvcBuilders.standaloneSetup(reportResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Report createEntity(EntityManager em) {
        Report report = new Report()
            .tsm(DEFAULT_TSM)
            .domain(DEFAULT_DOMAIN)
            .schedule(DEFAULT_SCHEDULE)
            .server(DEFAULT_SERVER)
            .datetimefalla(DEFAULT_DATETIMEFALLA)
            .status(DEFAULT_STATUS)
            .rework(DEFAULT_REWORK)
            .error(DEFAULT_ERROR)
            .code(DEFAULT_CODE)
            .datetimereworkini(DEFAULT_DATETIMEREWORKINI)
            .datetimereworkfin(DEFAULT_DATETIMEREWORKFIN)
            .ticket(DEFAULT_TICKET)
            .comment(DEFAULT_COMMENT)
            .user(DEFAULT_USER)
            .act(DEFAULT_ACT);
        return report;
    }

    @Before
    public void initTest() {
        report = createEntity(em);
    }

    @Test
    @Transactional
    public void createReport() throws Exception {
        int databaseSizeBeforeCreate = reportRepository.findAll().size();

        // Create the Report
        restReportMockMvc.perform(post("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isCreated());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeCreate + 1);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getTsm()).isEqualTo(DEFAULT_TSM);
        assertThat(testReport.getDomain()).isEqualTo(DEFAULT_DOMAIN);
        assertThat(testReport.getSchedule()).isEqualTo(DEFAULT_SCHEDULE);
        assertThat(testReport.getServer()).isEqualTo(DEFAULT_SERVER);
        assertThat(testReport.getDatetimefalla()).isEqualTo(DEFAULT_DATETIMEFALLA);
        assertThat(testReport.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testReport.getRework()).isEqualTo(DEFAULT_REWORK);
        assertThat(testReport.getError()).isEqualTo(DEFAULT_ERROR);
        assertThat(testReport.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testReport.getDatetimereworkini()).isEqualTo(DEFAULT_DATETIMEREWORKINI);
        assertThat(testReport.getDatetimereworkfin()).isEqualTo(DEFAULT_DATETIMEREWORKFIN);
        assertThat(testReport.getTicket()).isEqualTo(DEFAULT_TICKET);
        assertThat(testReport.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testReport.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testReport.getAct()).isEqualTo(DEFAULT_ACT);
    }

    @Test
    @Transactional
    public void createReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportRepository.findAll().size();

        // Create the Report with an existing ID
        report.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportMockMvc.perform(post("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReports() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);

        // Get all the reportList
        restReportMockMvc.perform(get("/api/reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(report.getId().intValue())))
            .andExpect(jsonPath("$.[*].tsm").value(hasItem(DEFAULT_TSM.toString())))
            .andExpect(jsonPath("$.[*].domain").value(hasItem(DEFAULT_DOMAIN.toString())))
            .andExpect(jsonPath("$.[*].schedule").value(hasItem(DEFAULT_SCHEDULE.toString())))
            .andExpect(jsonPath("$.[*].server").value(hasItem(DEFAULT_SERVER.toString())))
            .andExpect(jsonPath("$.[*].datetimefalla").value(hasItem(sameInstant(DEFAULT_DATETIMEFALLA))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].rework").value(hasItem(DEFAULT_REWORK.toString())))
            .andExpect(jsonPath("$.[*].error").value(hasItem(DEFAULT_ERROR.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].datetimereworkini").value(hasItem(sameInstant(DEFAULT_DATETIMEREWORKINI))))
            .andExpect(jsonPath("$.[*].datetimereworkfin").value(hasItem(sameInstant(DEFAULT_DATETIMEREWORKFIN))))
            .andExpect(jsonPath("$.[*].ticket").value(hasItem(DEFAULT_TICKET.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.toString())))
            .andExpect(jsonPath("$.[*].act").value(hasItem(DEFAULT_ACT.toString())));
    }

    @Test
    @Transactional
    public void getReport() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);

        // Get the report
        restReportMockMvc.perform(get("/api/reports/{id}", report.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(report.getId().intValue()))
            .andExpect(jsonPath("$.tsm").value(DEFAULT_TSM.toString()))
            .andExpect(jsonPath("$.domain").value(DEFAULT_DOMAIN.toString()))
            .andExpect(jsonPath("$.schedule").value(DEFAULT_SCHEDULE.toString()))
            .andExpect(jsonPath("$.server").value(DEFAULT_SERVER.toString()))
            .andExpect(jsonPath("$.datetimefalla").value(sameInstant(DEFAULT_DATETIMEFALLA)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.rework").value(DEFAULT_REWORK.toString()))
            .andExpect(jsonPath("$.error").value(DEFAULT_ERROR.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.datetimereworkini").value(sameInstant(DEFAULT_DATETIMEREWORKINI)))
            .andExpect(jsonPath("$.datetimereworkfin").value(sameInstant(DEFAULT_DATETIMEREWORKFIN)))
            .andExpect(jsonPath("$.ticket").value(DEFAULT_TICKET.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.toString()))
            .andExpect(jsonPath("$.act").value(DEFAULT_ACT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReport() throws Exception {
        // Get the report
        restReportMockMvc.perform(get("/api/reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReport() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();

        // Update the report
        Report updatedReport = reportRepository.findOne(report.getId());
        // Disconnect from session so that the updates on updatedReport are not directly saved in db
        em.detach(updatedReport);
        updatedReport
            .tsm(UPDATED_TSM)
            .domain(UPDATED_DOMAIN)
            .schedule(UPDATED_SCHEDULE)
            .server(UPDATED_SERVER)
            .datetimefalla(UPDATED_DATETIMEFALLA)
            .status(UPDATED_STATUS)
            .rework(UPDATED_REWORK)
            .error(UPDATED_ERROR)
            .code(UPDATED_CODE)
            .datetimereworkini(UPDATED_DATETIMEREWORKINI)
            .datetimereworkfin(UPDATED_DATETIMEREWORKFIN)
            .ticket(UPDATED_TICKET)
            .comment(UPDATED_COMMENT)
            .user(UPDATED_USER)
            .act(UPDATED_ACT);

        restReportMockMvc.perform(put("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReport)))
            .andExpect(status().isOk());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getTsm()).isEqualTo(UPDATED_TSM);
        assertThat(testReport.getDomain()).isEqualTo(UPDATED_DOMAIN);
        assertThat(testReport.getSchedule()).isEqualTo(UPDATED_SCHEDULE);
        assertThat(testReport.getServer()).isEqualTo(UPDATED_SERVER);
        assertThat(testReport.getDatetimefalla()).isEqualTo(UPDATED_DATETIMEFALLA);
        assertThat(testReport.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReport.getRework()).isEqualTo(UPDATED_REWORK);
        assertThat(testReport.getError()).isEqualTo(UPDATED_ERROR);
        assertThat(testReport.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testReport.getDatetimereworkini()).isEqualTo(UPDATED_DATETIMEREWORKINI);
        assertThat(testReport.getDatetimereworkfin()).isEqualTo(UPDATED_DATETIMEREWORKFIN);
        assertThat(testReport.getTicket()).isEqualTo(UPDATED_TICKET);
        assertThat(testReport.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReport.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testReport.getAct()).isEqualTo(UPDATED_ACT);
    }

    @Test
    @Transactional
    public void updateNonExistingReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();

        // Create the Report

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReportMockMvc.perform(put("/api/reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isCreated());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteReport() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);
        int databaseSizeBeforeDelete = reportRepository.findAll().size();

        // Get the report
        restReportMockMvc.perform(delete("/api/reports/{id}", report.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Report.class);
        Report report1 = new Report();
        report1.setId(1L);
        Report report2 = new Report();
        report2.setId(report1.getId());
        assertThat(report1).isEqualTo(report2);
        report2.setId(2L);
        assertThat(report1).isNotEqualTo(report2);
        report1.setId(null);
        assertThat(report1).isNotEqualTo(report2);
    }
}
