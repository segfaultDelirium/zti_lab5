package zti.aspect;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import zti.model.Person;

@Aspect
@Component
public class AspectLogger {

    @Before("execution(* zti.model.PersonDaoImpl.getPeople(..))")
    public void logBefore1(JoinPoint joinPoint) {
        System.out.println(" *** Advice Before - (logBefore1) jest wykonany !");
        System.out.println(" *** Metoda : " + joinPoint.getSignature().getName()
                + "() rozpoczyna się od " + Arrays.toString(joinPoint.getArgs()));
    }

    @Pointcut("execution(* zti.model.*.*(..))")
    private void getPeople() {
    }

    @AfterReturning(pointcut = "getPeople()", returning = "retVal")
    public void afterReturningMethod1(JoinPoint joinPoint, Object retVal) {
        System.out.println(" *** Advice AfterReturning - (afterReturningMethod1) jest wykonany !");
        System.out.println(" *** Metoda " + joinPoint.getSignature().getName() + " zwróciła wartość : " + retVal.toString());
    }



    @Around("execution(* zti.model.PersonDaoImpl.getPersonByID(..))")
    public Object beforeListPerson(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(" *** Advice Around - (beforeListPerson) jest wykonawany !");;
        Object[] args = pjp.getArgs();
        if(args.length>0){
            System.out.print(" *** Dopasowane argumenty : " );
            for (int i = 0; i < args.length; i++) {
                System.out.print("arg "+(i+1)+": "+args[i].toString());
            }
        }

        Person result = (Person) pjp.proceed(args);
        result.setLname(result.getLname().toUpperCase());
        System.out.println(" *** Zwrócona wartość : " + result);
        return result;
    }

    @Pointcut("target(zti.model.PersonDaoImpl)")
    public void repositoryClassMethods() {};

    @Around("repositoryClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object retval = pjp.proceed();
        long end = System.nanoTime();
        String methodName = pjp.getSignature().getName();
        System.out.print(" *** Czas wykonania " + methodName + " wynosi " +
                TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }


}