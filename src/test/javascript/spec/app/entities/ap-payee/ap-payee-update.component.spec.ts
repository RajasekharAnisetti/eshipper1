import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayeeUpdateComponent } from 'app/entities/ap-payee/ap-payee-update.component';
import { ApPayeeService } from 'app/entities/ap-payee/ap-payee.service';
import { ApPayee } from 'app/shared/model/ap-payee.model';

describe('Component Tests', () => {
  describe('ApPayee Management Update Component', () => {
    let comp: ApPayeeUpdateComponent;
    let fixture: ComponentFixture<ApPayeeUpdateComponent>;
    let service: ApPayeeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayeeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ApPayeeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApPayeeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayeeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApPayee(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApPayee();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
