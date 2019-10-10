import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayeeTypeDetailComponent } from 'app/entities/ap-payee-type/ap-payee-type-detail.component';
import { ApPayeeType } from 'app/shared/model/ap-payee-type.model';

describe('Component Tests', () => {
  describe('ApPayeeType Management Detail Component', () => {
    let comp: ApPayeeTypeDetailComponent;
    let fixture: ComponentFixture<ApPayeeTypeDetailComponent>;
    const route = ({ data: of({ apPayeeType: new ApPayeeType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayeeTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApPayeeTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApPayeeTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.apPayeeType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
