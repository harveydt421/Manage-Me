/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ManageMeTestModule } from '../../../test.module';
import { SeparationApplicationUpdateComponent } from 'app/entities/separation-application/separation-application-update.component';
import { SeparationApplicationService } from 'app/entities/separation-application/separation-application.service';
import { SeparationApplication } from 'app/shared/model/separation-application.model';

describe('Component Tests', () => {
    describe('SeparationApplication Management Update Component', () => {
        let comp: SeparationApplicationUpdateComponent;
        let fixture: ComponentFixture<SeparationApplicationUpdateComponent>;
        let service: SeparationApplicationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManageMeTestModule],
                declarations: [SeparationApplicationUpdateComponent]
            })
                .overrideTemplate(SeparationApplicationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SeparationApplicationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SeparationApplicationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SeparationApplication(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.separationApplication = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SeparationApplication();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.separationApplication = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
