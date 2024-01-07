package com.booking.project.service;

import com.booking.project.model.Analytics;
import com.booking.project.service.interfaces.IAnalyticsService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService implements IAnalyticsService {
    @Autowired
    EntityManager em;
    @Override
    public Analytics getAnnualAnalytics(int year, Long accommodationId, Long  hostUserId){
        Query q = em.createQuery("select month(r.startDate), year(r.startDate), count(r), sum(r.price) from Reservation r join  r.accommodation a  " +
                "where  r.status = 'ACCEPTED' and  a.host.user.id = :hostUserId and a.id = :accommodationId and year(r.startDate) = :year " +
                "group by a.id, month(r.startDate), year(r.startDate) ");

        q.setParameter("year", year);
        q.setParameter("accommodationId", accommodationId);
        q.setParameter("hostUserId", hostUserId);

        List<Object[]> annualAnalytics = q.getResultList();
        Analytics analytics = new Analytics();

        Long totalReservations = 0L;
        Double totalEarnings = 0.0;

        for(Object[] analytic: annualAnalytics){
            int i = (int) analytic[0] - 1;
            analytics.reservationsPerMonth[i] = (Long) analytic[2];
            totalReservations += analytics.reservationsPerMonth[i];

            analytics.earningsPerMonth[i] = (Double) analytic[3];
            totalEarnings += analytics.earningsPerMonth[i];
        }
        analytics.setTotalEarnings(totalEarnings);
        analytics.setTotalReservations(totalReservations);

        return analytics;
    }
}
