import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { SessionService } from '../services/session.service';

export const tenantInterceptor: HttpInterceptorFn = (req, next) => {
  const sessionService = inject(SessionService);
  const tenantId = sessionService.getTenantId();

  // Skip adding tenant for auth endpoints
  if (req.url.includes('/auth/')) {
    return next(req);
  }

  // Add tenant ID to headers if user is logged in
  if (tenantId) {
    const clonedRequest = req.clone({
      setHeaders: {
        'X-Tenant-ID': tenantId.toString()
      },
      withCredentials: true
    });
    return next(clonedRequest);
  }

  return next(req);
};