import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { WoPaymentMethodDetailComponent } from 'app/entities/wo-payment-method/wo-payment-method-detail.component';
import { WoPaymentMethod } from 'app/shared/model/wo-payment-method.model';

describe('Component Tests', () => {
  describe('WoPaymentMethod Management Detail Component', () => {
    let comp: WoPaymentMethodDetailComponent;
    let fixture: ComponentFixture<WoPaymentMethodDetailComponent>;
    const route = ({ data: of({ woPaymentMethod: new WoPaymentMethod(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [WoPaymentMethodDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoPaymentMethodDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoPaymentMethodDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woPaymentMethod).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
