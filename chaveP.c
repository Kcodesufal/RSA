#include <stdio.h>
#include <stdlib.h>

int algEuclides(long long int a, long long int b)
{
    if(a%b == 0)
    {
        return b;
    }
    else
    {
        return algEuclides(b,a%b);
    }
}

int ehPrimo(long long int n)
{
    int primo = 1;
    if(n < 2)
    {
        primo = 0;
    }
    else if(n < 4)
    {
        primo = 1;
    }
    else
    {
        for(int i = 2; i <= n/2; i++)
        {
            if(n%i == 0)
            {
                primo = 0;
            }
        }
    }
    return primo;
}

void erro()
{
    FILE *fp;
    fp = fopen("paraBack/verifica.txt", "w");
    fprintf(fp, "erro");
    fclose(fp);
}

void valido()
{
    FILE *fp;
    fp = fopen("paraBack/verifica.txt", "w");
    fprintf(fp, "valido");
    fclose(fp);
}

long long int lerP()
{
    int n;
    FILE *fp;
    fp = fopen("paraBack/p.txt", "r");

    fseek(fp,0,SEEK_END);

    if(ftell(fp) == 0)
    {
        return 0;
    }
    else
    {
        rewind(fp);
        fscanf(fp, "%lld", &n);
        fclose(fp);
        return n;
    }
}

long long int lerQ()
{
    long long int n;
    FILE *fp;
    fp = fopen("paraBack/q.txt", "r");
    
    fseek(fp,0,SEEK_END);
    
    if(ftell(fp) == 0)
    {
        return 0;
    }
    else
    {
        rewind(fp);
        fscanf(fp, "%lld", &n);
        fclose(fp);
        return n;
    }
}

long long int lerE()
{
    long long int n;
    FILE *fp;
    fp = fopen("paraBack/e.txt", "r");
    
    fseek(fp,0,SEEK_END);
    
    if(ftell(fp) == 0)
    {
        return 0;
    }
    else
    {
        rewind(fp);
        fscanf(fp, "%lld", &n);
        fclose(fp);
        return n;
    }
}

void chPubl(long long int n, long long int e)
{
    FILE *fp;
    fp = fopen("ChavePublica.txt", "w");
    fprintf(fp, "%lld %lld", n, e);
    fclose(fp);
}

 int main()
 {
    long long int p = lerP();
    long long int q = lerQ();
    long long int e = lerE();
    long long int z = (p-1)*(q-1);

    if(ehPrimo(p) == 1 && ehPrimo(q) == 1 && p != q && algEuclides(e,z) == 1)
    {
        valido();
        long long int n = p*q;
        chPubl(n,e);
    }
    else
    {
        erro();
    }

    return 0;
 }
