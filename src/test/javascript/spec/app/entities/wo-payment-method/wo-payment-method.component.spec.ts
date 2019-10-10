import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Eshipper1TestModule } from '../../../test.module';
import { WoPaymentMethodComponent } from 'app/entities/wo-payment-method/wo-payment-method.component';
import { WoPaymentMethodService } from 'app/entities/wo-payment-method/wo-payment-method.service';
import { WoPaymentMethod } from 'app/shared/model/wo-payment-method.model';

describe('Component Tests', () => {
  describe('WoPaymentMethod Management Component', () => {
    let comp: WoPaymentMethodComponent;
    let fixture: ComponentFixture<WoPaymentMethodComponent>;
    let service: WoPaymentMethodService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [WoPaymentMethodComponent],
        providers: []
      })
        .overrideTemplate(WoPaymentMethodComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoPaymentMethodComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoPaymentMethodService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoPaymentMethod(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woPaymentMethods[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
