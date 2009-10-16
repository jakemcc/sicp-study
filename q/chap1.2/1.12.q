nextrow:{[p] 2 msum p, 0};
pth:{[p; n] $[0 = n; p; pth[nextrow p;n-1]]};
pt:{[n] $[n = 0; 1; pth[1; n]]};

show pt 0;
show pt 1;
show pt 2;
show pt 3;
show pt 4;
show pt 5;
