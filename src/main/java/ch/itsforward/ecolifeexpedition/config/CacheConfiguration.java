package ch.itsforward.ecolifeexpedition.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, ch.itsforward.ecolifeexpedition.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, ch.itsforward.ecolifeexpedition.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.User.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Authority.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.User.class.getName() + ".authorities");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeCircuit.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeCircuit.class.getName() + ".tours");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Tour.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Tour.class.getName() + ".serviceSupplementaires");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.ServiceSupplementaire.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Agence.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Agence.class.getName() + ".hotels");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.DonneurOrdre.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.DonneurOrdre.class.getName() + ".reservations");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Client.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Reservation.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Reservation.class.getName() + ".chambres");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Reservation.class.getName() + ".clients");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Hotel.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Hotel.class.getName() + ".chambres");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Chambre.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeChambre.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeChambre.class.getName() + ".chambres");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Pays.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Pays.class.getName() + ".regions");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Region.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Region.class.getName() + ".tours");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeTarif.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeTarif.class.getName() + ".tarifTransferts");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TarifTransfert.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeTransfert.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeTransfert.class.getName() + ".transferts");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeTransfert.class.getName() + ".tarifTransferts");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Transfert.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Transfert.class.getName() + ".avisTransferts");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.AvisTransfert.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeTarif.class.getName() + ".tarifHebergements");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Transfert.class.getName() + ".reservationTransferts");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.ReservationTour.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.ReservationHebergement.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.ReservationTransfert.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TarifHebergment.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeHebergement.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeHebergement.class.getName() + ".hebergements");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Hebergement.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Hebergement.class.getName() + ".reservationHebergements");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Hebergement.class.getName() + ".avisHebergements");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Hotel.class.getName() + ".hotelMedias");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.HotelMedia.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TarifHebergement.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.AvisTour.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.AvisTour.class.getName() + ".avisTourMedias");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.AvisTourMedia.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.AvisHebergement.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.AvisHebergement.class.getName() + ".avisHebergementMedias");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.AvisHebergementMedia.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Region.class.getName() + ".pays");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeRegion.class.getName());
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.TypeRegion.class.getName() + ".regions");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Pays.class.getName() + ".listRegions");
            createCache(cm, ch.itsforward.ecolifeexpedition.domain.Region.class.getName() + ".listPays");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
